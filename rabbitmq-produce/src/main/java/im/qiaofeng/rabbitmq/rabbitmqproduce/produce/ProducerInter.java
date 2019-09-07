package im.qiaofeng.rabbitmq.rabbitmqproduce.produce;

import im.qiaofeng.rabbitmq.rabbitmqconfig.msg.Msg;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.connection.CorrelationData;

/**
 * @author qiaofeng
 */
public interface ProducerInter {

    void sendDirectMsg(String exchangeName, String routingKey, Msg msg, CorrelationData msgId);

    void sendFanoutMsg(String exchangeName, Msg msg);
}