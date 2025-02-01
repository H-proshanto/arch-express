package com.archexpress.Demo.queue;

import com.archexpress.Demo.employee.database.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);

    @RabbitListener(queues = "def-queue")
    public void receiveMessage(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        Employee employee;
        try {
            employee = objectMapper.readValue(message, Employee.class);
            log.info("Employee received: {}", employee);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Received Employee: " + employee);
    }
}

