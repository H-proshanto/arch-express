package com.archexpress.Demo.queue;

import com.archexpress.Demo.queue.comnnads.AddPassengerCommand;
import com.archexpress.Demo.queue.handlers.AddPassengerCommandHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CommandDispatcher {
   private static final Logger log = LoggerFactory.getLogger(CommandDispatcher.class);
    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AddPassengerCommandHandler addPassengerCommandHandler;



//    public <C extends Command> void handle(String commandStr) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        C command;
//        try {
//            command = objectMapper.readValue(commandStr, C.class);
//            this.dispatch(command);
//            log.info("Command received and dispatched: {}", command);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//    }


    public void dispatch(AddPassengerCommand command) {
        addPassengerCommandHandler.handle(command);
    }
}