package im.qiaofeng.rabbitmq.rabbitmqproduce.produce;

import im.qiaofeng.rabbitmq.rabbitmqconfig.msg.Msg;
import im.qiaofeng.rabbitmq.rabbitmqproduce.ack.MsgSendConfirmCallBack;
import im.qiaofeng.rabbitmq.rabbitmqproduce.ack.MsgSendReturnCallback;
import javax.annotation.PostConstruct;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 生产者实现类，产生消息
 *
 * @author qiaofeng
 */
@Transactional
@Service
public class ProduceImpl implements ProducerInter {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void initProduceImpl(){
        //设置produce-exchange的回调
        rabbitTemplate.setConfirmCallback(new MsgSendConfirmCallBack());

        //设置当exchange-》queue失败时，回调方法
        rabbitTemplate.setReturnCallback(new MsgSendReturnCallback());
    }

    @Override
    public void sendDirectMsg(String exchangeName, String routingKey, Msg msg, CorrelationData msgId) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, msg, msgId);
    }

    @Override
    public void sendFanoutMsg(String exchangeName, Msg msg) {
        rabbitTemplate.convertAndSend(exchangeName, "", msg);
    }
}