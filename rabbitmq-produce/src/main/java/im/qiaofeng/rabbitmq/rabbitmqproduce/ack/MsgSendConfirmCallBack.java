package im.qiaofeng.rabbitmq.rabbitmqproduce.ack;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MsgSendConfirmCallBack implements RabbitTemplate.ConfirmCallback {

    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("MsgSendConfirmCallBack  , 回调id:" + correlationData);
        if (ack) {
            log.info("消息消费成功");
        } else {
            log.error("消息消费失败:" + cause+"\n重新发送");

        }
    }
}
