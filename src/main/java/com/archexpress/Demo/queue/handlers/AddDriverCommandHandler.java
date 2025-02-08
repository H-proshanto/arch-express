package com.archexpress.Demo.queue.handlers;

import com.archexpress.Demo.aggregateRoots.DriverAggregateRoot;
import com.archexpress.Demo.aggregateRoots.Event;
import com.archexpress.Demo.aggregateRoots.PassengerAggregateRoot;
import com.archexpress.Demo.driver.DriverRepository;
import com.archexpress.Demo.passenger.PassengerRepository;
import com.archexpress.Demo.queue.ServiceBus;
import com.archexpress.Demo.queue.comnnads.AddDriverCommand;
import io.lettuce.core.AbstractRedisAsyncCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddDriverCommandHandler implements CommandHandler<AddDriverCommand> {
    @Autowired
    private ServiceBus serviceBus;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public void handle(AddDriverCommand command) {
        DriverAggregateRoot driverAggregateRoot = new DriverAggregateRoot();
        List<Event> events = new ArrayList<>();
        driverAggregateRoot.create(command, events);


//        save passengerAggregateRoot --> repo
        driverRepository.save(driverAggregateRoot);
        serviceBus.broadcast(events, "driver_registration_exchange");
    }
}