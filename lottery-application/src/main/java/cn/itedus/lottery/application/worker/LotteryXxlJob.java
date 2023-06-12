package cn.itedus.lottery.application.worker;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.bugstack.middleware.db.router.strategy.IDBRouterStrategy;
import cn.itedus.lottery.application.mq.producer.KafkaProducer;
import cn.itedus.lottery.common.Constants;
import cn.itedus.lottery.common.Result;
import cn.itedus.lottery.domain.activity.model.vo.ActivityVO;
import cn.itedus.lottery.domain.activity.model.vo.InvoiceVO;
import cn.itedus.lottery.domain.activity.service.deploy.IActivityDeploy;
import cn.itedus.lottery.domain.activity.service.partake.IActivityPartake;
import cn.itedus.lottery.domain.activity.service.stateflow.IStateHandler;
import com.alibaba.fastjson.JSON;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Component
public class LotteryXxlJob {
    private Logger logger = LoggerFactory.getLogger(LotteryXxlJob.class);

    //部署活动配置接口
    @Resource
    private IActivityDeploy activityDeploy;

    //活动状态处理接口
    @Resource
    private IStateHandler stateHandler;

    @Resource
    private IDBRouterStrategy dbRouter;

    @Resource
    private IActivityPartake activityPartake;

    @Resource
    private KafkaProducer kafkaProducer;


    @XxlJob("lotteryActivityStateJobHandler")
    public void lotteryActivityStateJobHandler() throws Exception{
        logger.info("扫描活动状态 Begin");

        List<ActivityVO> activityVOList = activityDeploy.scanToDoActivityList(0L);
        if (activityVOList.isEmpty()){
            logger.info("扫描活动状态 End 暂无符合需要扫描的活动列表");
            return;
        }
        while(!activityVOList.isEmpty()){
            for(ActivityVO activityVO: activityVOList){
                Integer state = activityVO.getState();
                switch (state){
                    case 4:
                        Result state4Result = stateHandler.doing(activityVO.getActivityId(), Constants.ActivityState.PASS);
                        logger.info("扫描活动状态为活动中 结果：{} activityId：{} activityName：{} creator：{}", JSON.toJSONString(state4Result), activityVO.getActivityId(), activityVO.getActivityName(), activityVO.getCreator());
                        break;
                    //扫描过期的活动
                    case 5:
                        if (activityVO.getEndDateTime().before(new Date())){
                        Result state5Result = stateHandler.close(activityVO.getActivityId(), Constants.ActivityState.DOING);
                        logger.info("扫描活动状态为关闭 结果：{} activityId：{} activityName：{} creator：{}", JSON.toJSONString(state5Result), activityVO.getActivityId(), activityVO.getActivityName(), activityVO.getCreator());
                        }
                        break;
                    default:
                        break;
                    }
                }
            // 获取集合中最后一条记录，继续扫描后面10条记录
            ActivityVO activityVO = activityVOList.get(activityVOList.size() - 1);
            activityVOList = activityDeploy.scanToDoActivityList(activityVO.getId());

            }
        logger.info("扫描活动状态 End");
        }

        //消息补偿任务
        @XxlJob("lotteryOrderMQStateJobHandler")
        public void lotteryOrderMQStateJobHandler() throws Exception{
        //验证参数
            String jobParam = XxlJobHelper.getJobParam();
            if (null == jobParam) {
                logger.info("扫描用户抽奖奖品发放MQ状态[Table = 2*4] 错误 params is null");
                return;
            }

            // 获取分布式任务配置参数信息 参数配置格式：1，2，3
            //也可以是指定扫描一个，也可以配置多个库，按照部署的任务集群进行数量配置，均摊分别扫描效率更高
            String[] params = jobParam.split(",");//这句话的意思是：根据给定的正则表达式来分割该字符串。该方法的运行方式就像是使用给定的表达式和限制参数为0调用双参数 split 方法一样。因此，尾随的空字符串不会包含在生成的数组中。
            logger.info("扫描用户抽奖奖品发放MQ状态[Table = 2*4] 开始 params：{}", JSON.toJSONString(params));
            if (params.length == 0) {
                logger.info("扫描用户抽奖奖品发放MQ状态[Table = 2*4] 结束 params is null");
                return;
            }

            //获取分库分表配置下的分表数
            int tbCount = dbRouter.tbCount();
            //循环获取指定扫描库
            for(String param :params){
                //获取当前任务扫描的指定分库
                int dbCount = Integer.parseInt(param);
                //获取配置指定扫描库数，是否存在
                if(dbCount > dbRouter.dbCount()){
                    logger.info("扫描用户抽奖奖品发放MQ状态[Table = 2*4] 结束 dbCount not exist");
                    continue;
                }

                //循环扫描对应表
                for(int i = 0; i < tbCount; i++){
                    //扫描库表数据
                    List<InvoiceVO> invoiceVOList = activityPartake.scanInvoiceMqState(dbCount,i);
                    logger.info("扫描用户抽奖奖品发放MQ状态[Table = 2*4] 扫描库：{} 扫描表：{} 扫描数：{}", dbCount, i, invoiceVOList.size());

                    //补偿MQ消息

                    for(InvoiceVO invoiceVO : invoiceVOList){
                        ListenableFuture <org.springframework.kafka.support.SendResult<String, Object>> future = kafkaProducer.sendLotteryInvoice(invoiceVO);
                        future.addCallback(new ListenableFutureCallback <org.springframework.kafka.support.SendResult<String, Object>>() {
                            @Override
                            public void onSuccess(SendResult<String, Object> result) {
                            //MQ消息发送完成，更新数据库表 user_strategy_export.mq_state = 1
                                activityPartake.updateInvoiceMqState(invoiceVO.getuId(), invoiceVO.getOrderId(),Constants.MQState.COMPLETE.getCode() );
                            }

                            @Override
                            public void onFailure(Throwable ex) {
                                // MQ 消息发送失败，更新数据库表 user_strategy_export.mq_state = 2 【等待定时任务扫码补偿MQ消息】
                                activityPartake.updateInvoiceMqState(invoiceVO.getuId(), invoiceVO.getOrderId(), Constants.MQState.FAIL.getCode());

                            }

                        });
                    }
                }
            }
        }


}
