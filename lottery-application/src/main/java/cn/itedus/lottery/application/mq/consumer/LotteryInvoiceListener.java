package cn.itedus.lottery.application.mq.consumer;

import cn.hutool.core.lang.Assert;
import cn.itedus.lottery.common.Constants;
import cn.itedus.lottery.domain.activity.model.vo.InvoiceVO;
import cn.itedus.lottery.domain.award.model.req.GoodsReq;
import cn.itedus.lottery.domain.award.model.res.DistributionRes;
import cn.itedus.lottery.domain.award.services.factory.DistributionGoodsFactory;
import cn.itedus.lottery.domain.award.services.goods.IDistributionGoods;
import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 中奖发货单监听消息
 */
//在这个 LotteryInvoiceListener 消息监听类中，主要就是通过消息中的发奖类型获取到对应的奖品发货工厂，处理奖品的发送操作。
@Component
public class LotteryInvoiceListener {
    private Logger logger = LoggerFactory.getLogger(LotteryInvoiceListener.class);

    //奖品有不同的类型，GoodsConfig包含不同奖品类型的类，DistributionGoodsFactory是GoodsConfig的子类，
    // 方法通过入参 返回map键值对里的 接口变量
    @Resource
    private DistributionGoodsFactory distributionGoodsFactory;

    @KafkaListener(topics = "lottery_invoice",groupId = "lottery")
    public void onMessage(ConsumerRecord<?,?>record ,Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        Optional<?> message = Optional.ofNullable(record.value());
    //1. 判断消息是否存在
        if(!message.isPresent())
            return;

    //2. 处理MQ消息

        try {
            //1.转化对象（中奖物品发货单）
            InvoiceVO invoiceVO = JSON.parseObject((String) message.get(), InvoiceVO.class);

            //2.获取发送奖品工厂执行发奖
            IDistributionGoods distributionGoodsServer = distributionGoodsFactory.getDistributionGoodsService(invoiceVO.getAwardType());
            DistributionRes distributionRes = distributionGoodsServer.doDistribution(new GoodsReq(invoiceVO.getuId(), invoiceVO.getOrderId(), invoiceVO.getAwardId(),invoiceVO.getAwardName(),invoiceVO.getAwardContent()));

            Assert.isTrue(Constants.AwardState.SUCCESS.getCode().equals(distributionRes.getCode()),distributionRes.getInfo());

            //3. 打印日志
            logger.info("消费MQ消息，完成 topic：{} bizId：{} 发奖结果：{}", topic, invoiceVO.getuId(), JSON.toJSONString(distributionRes));

            //4. 消息消费完成
            ack.acknowledge();

        } catch (Exception e) {
            // 发奖环节失败，消息重试。所有到环节，发货、更新库，都需要保证幂等。
            logger.error("消费MQ消息，失败 topic：{} message：{}", topic, message.get());
            throw e;

        }
    }

}
