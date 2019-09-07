package im.qiaofeng.rabbitmq.rabbitmqconfig.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq连接配置
 *
 * @author qiaofeng
 */
@Configuration
@EnableRabbit
public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${spring.rabbitmq.listener.simple.concurrency}")
    private int consumer;

    @Value("${spring.rabbitmq.listener.simple.max-concurrency}")
    private int maxConsumer;

    /**
     * 配置连接对象
     * @return 连接对象
     */
    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);

        connectionFactory.setPublisherReturns(true);
        connectionFactory.setPublisherConfirms(true);

        return connectionFactory;
    }

    /**
     * 配置操作模版rabbitmqTemplate
     */
    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        rabbitTemplate.setMandatory(true);

        return rabbitTemplate;
    }

    /**
     * 配置消费者监听器
     */
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrentConsumers(consumer);
        factory.setMaxConcurrentConsumers(maxConsumer);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);

        return factory;
    }




























}