package com.archexpress.Demo.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue defQueue() {
        return new Queue("def-queue", false);
    }

    @Bean
    public TopicExchange defExchange() {
        return new TopicExchange("def-exchange");
    }

    @Bean
    public Binding defBinding(Queue defQueue, TopicExchange defExchange) {
        return BindingBuilder.bind(defQueue).to(defExchange).with("def-routing-key");
    }

    @Bean
    public Queue passengerQueue() {
        return new Queue("passenger_registration_exchange", false);
    }

    @Bean
    public TopicExchange passengerExchange() {
        return new TopicExchange("passenger_registration_exchange");
    }

    @Bean
    public Binding passengerBinding(Queue passengerQueue, TopicExchange passengerExchange) {
        return BindingBuilder.bind(passengerQueue).to(passengerExchange).with("passenger_registration_exchange");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
