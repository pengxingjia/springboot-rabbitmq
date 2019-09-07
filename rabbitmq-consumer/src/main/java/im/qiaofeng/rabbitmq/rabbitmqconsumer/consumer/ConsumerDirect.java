package im.qiaofeng.rabbitmq.rabbitmqconsumer.consumer;

import com.rabbitmq.client.Channel;
import im.qiaofeng.rabbitmq.rabbitmqconfig.msg.Msg;
import java.io.IOException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * @author qiaofeng
 */
@Component
public class ConsumerDirect {


    @RabbitListener(queues = "testQueue1")
    @RabbitHandler
    public void consumerOneQueue(Msg msg, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag){
        System.out.println("消费到队列testQueueOne的消息1:" + msg.toString());
        try {
            channel.basicAck(tag, false);
        } catch (IOException e) {
            System.out.println("手动消息确认消费失败" +e.getMessage());
        }
    }

    @RabbitListener(queues = "testQueue2")
    @RabbitHandler
    public void consumerTwoQueue(Msg msg, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag){
        System.out.println("消费到队列testQueueOne的消息2:" + msg.toString());
        try {
            channel.basicAck(tag, false);
        } catch (IOException e) {
            System.out.println("手动消息确认消费失败" +e.getMessage());
        }
    }
}