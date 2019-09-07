package im.qiaofeng.rabbitmq.rabbitmqproduce.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置队列和交换机绑定   direct模式
 *
 * @author qiaofeng
 */
@Configuration
public class ProduceConfigDirect {

    /**
     *  创建消息队列
     */

    @Bean
    public Queue testQueueOne(){
        String testQueue1 = "testQueue1";
        return new Queue(testQueue1);
    }

    @Bean
    public Queue testQueueTwo(){
        String testQueue2 = "testQueue2";
        return new Queue(testQueue2);
    }

    /**
     *  创建交换机，交换机分为fanout、direct、topic
     */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("testExchange");
    }

    /**
     * 将队列与交换机进行绑定
     */
    @Bean
    public Binding bindingOne(){
        return  BindingBuilder.bind(testQueueOne()).to(directExchange()).with("testRouting1");
    }

    @Bean
    public Binding bindingTwo(){
        return  BindingBuilder.bind(testQueueTwo()).to(directExchange()).with("testRouting2");
    }
}