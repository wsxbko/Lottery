package cn.itedus.lottery.application.mq.producer;

import cn.itedus.lottery.domain.activity.model.vo.ActivityPartakeRecordVO;
import cn.itedus.lottery.domain.activity.model.vo.InvoiceVO;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 * 消息生产者
 */
@Component
public class KafkaProducer {
    private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Resource
    private KafkaTemplate<String,Object> kafkaTemplate;


    /**
     * MQ主题：活动领取记录
     *
     * 创建topic：bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic lottery_activity_partake
     */
    public static final String TOPIC_ACTIVITY_PARTAKE = "lottery_activity_partake";

    /**
     * MQ主题：中奖发货单
     */
    public static final String TOPIC_INVOICE = "lottery_invoice";

    //把所有的生产消息都放到 KafkaProducer 中，并对外提供一个可以发送 MQ 消息的方法
    public ListenableFuture<SendResult<String,Object>> sendLotteryInvoice(InvoiceVO invoice){
        String objJson = JSON.toJSONString(invoice);

        logger.info("发送MQ消息 topic：{} bizId：{} message：{}", TOPIC_INVOICE, invoice.getuId(), objJson);
        return kafkaTemplate.send(TOPIC_INVOICE, objJson);

    }

    /**
     * 发送领取活动记录MQ
     *
     * @param activityPartakeRecord 领取活动记录
     */
    public ListenableFuture<SendResult<String, Object>> sendLotteryActivityPartakeRecord(ActivityPartakeRecordVO activityPartakeRecord) {
        String objJson = JSON.toJSONString(activityPartakeRecord);
        logger.info("发送MQ消息(领取活动记录) topic：{} bizId：{} message：{}", TOPIC_ACTIVITY_PARTAKE, activityPartakeRecord.getuId(), objJson);
        return kafkaTemplate.send(TOPIC_ACTIVITY_PARTAKE, objJson);
    }

    public static final String TOPIC_TEST = "Hello-Kafka";


    public void send(Object obj){
        String obj2String = JSON.toJSONString(obj);
        logger.info("准备发送消息为：{}", obj2String);

        //发送消息
        ListenableFuture<SendResult<String,Object>> future = kafkaTemplate.send(TOPIC_TEST,obj);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                logger.info(TOPIC_TEST + " - 生产者 发送消息失败：" + throwable.getMessage());

            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                logger.info(TOPIC_TEST + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());

            }
        });



    }



}
