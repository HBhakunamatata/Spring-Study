package cloud.popples.messaging.config;

import cloud.popples.messaging.listener.JmsListener;
import cloud.popples.messaging.service.JmsService;
import cloud.popples.messaging.service.JmsServiceImpl;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.MessageListener;

@EnableJms
@Configuration
@ComponentScan("cloud.popples.messaging")
public class JmsConfig {

    @Bean
    public JmsService jmsService(JmsOperations jmsOperations) {
        return new JmsServiceImpl(jmsOperations);
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://localhost:61616");
        return connectionFactory;
    }

    @Bean
    public ActiveMQQueue activemqQueue() {
        return new ActiveMQQueue("spitter.alert.queue");
    }

    @Bean
    @Qualifier("simpleMessageConverter")
    public MessageConverter simpleMessageConverter() {
        return new SimpleMessageConverter();
    }

    @Bean
    @Qualifier("jsonMessageConverter")
    public MessageConverter jsonMessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory connectionFactory,
                                   ActiveMQQueue activemqQueue,
                                   @Qualifier("simpleMessageConverter") MessageConverter messageConverter) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestination(activemqQueue);
        jmsTemplate.setMessageConverter(messageConverter);
        return jmsTemplate;
    }

    @Bean
    public SimpleJmsListenerContainerFactory jmsListenerContainerFactory(ActiveMQConnectionFactory connectionFactory,
                                                                   @Qualifier("simpleMessageConverter") MessageConverter messageConverter) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }


//    @Bean
//    public MessageListenerContainer messageListenerContainer() {
//        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory());
//        container.setDestination(activemqQueue());
//        container.setMessageListener(messageListener());
//        container.setMessageConverter(simpleMessageConverter());
//        return container;
//    }
//
//
//    @Bean
//    public MessageListener messageListener() {
//        return new JmsListener();
//    }


}
