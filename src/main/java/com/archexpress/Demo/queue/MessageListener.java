package com.archexpress.Demo.queue;


import com.archexpress.Demo.queue.comnnads.AddPassengerCommand;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private CommandDispatcher commandDispatcher;

    @RabbitListener(queues = "passenger_registration_queue")
    public void receiveMessage(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        AddPassengerCommand command;
        try {
            command = objectMapper.readValue(message, AddPassengerCommand.class);
            commandDispatcher.dispatch(command);
            log.info("Command received and dispatched: {}", command);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}