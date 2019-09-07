package im.qiaofeng.rabbitmq.rabbitmqproduce.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fanout 模式
 *
 * @author qiaofeng
 */
@Configuration
public class ProduceConfigFanout {

    /**
     *  创建消息队列
     */

    @Bean
    public Queue testFanoutQueueOne(){
        String testQueue1 = "testFanoutQueue1";
        return new Queue(testQueue1);
    }

    @Bean
    public Queue testFanoutQueueTwo(){
        String testQueue2 = "testFanoutQueue2";
        return new Queue(testQueue2);
    }


    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("testFanoutExchange");
    }
    /**
     * 将队列与交换机进行绑定
     */
    @Bean
    public Binding bindingFanoutOne(){
        return  BindingBuilder.bind(testFanoutQueueOne()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingFanoutTwo(){
        return  BindingBuilder.bind(testFanoutQueueTwo()).to(fanoutExchange());
    }

}