package com.archexpress.Demo.queue;

import com.archexpress.Demo.aggregateRoots.Event;
import com.archexpress.Demo.exceptions.MessageSendingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceBus {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public <M extends Publishable> void publish(M messageObject, String exchangeName) {
        ObjectMapper objectMapper = new ObjectMapper();
        String message = null;
        try {
            message = objectMapper.writeValueAsString(messageObject);
        } catch (JsonProcessingException e) {
            throw new MessageSendingException("Failed to convert message object to JSON", e);
        }
        rabbitTemplate.convertAndSend(exchangeName, exchangeName, message);
    }

    public void broadcast(List<Event> events, String exchangeName){
        for (Event event : events) {
            publish(event, exchangeName);
        }
    }

    public <M extends Publishable> void publishToQueue(M messageObject, String queueName) {
        ObjectMapper objectMapper = new ObjectMapper();
        String message = null;
        try {
            message = objectMapper.writeValueAsString(messageObject);
        } catch (JsonProcessingException e) {
            throw new MessageSendingException("Failed to convert message object to JSON", e);
        }
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
