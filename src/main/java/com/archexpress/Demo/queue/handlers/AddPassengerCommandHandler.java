package com.archexpress.Demo.queue.handlers;


import com.archexpress.Demo.aggregateRoots.Event;
import com.archexpress.Demo.aggregateRoots.PassengerAggregateRoot;
import com.archexpress.Demo.queue.ServiceBus;
import com.archexpress.Demo.queue.comnnads.AddPassengerCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddPassengerCommandHandler implements CommandHandler<AddPassengerCommand> {
    @Autowired
    private ServiceBus serviceBus;

//    @Autowired
//    private IRepository<PassengerAggregateRoot> repository;

    @Override
    public void handle(AddPassengerCommand command) {
        PassengerAggregateRoot passengerAggregateRoot = new PassengerAggregateRoot();
        List<Event> events = new ArrayList<>();
        passengerAggregateRoot.create(command, events);



//        save passengerAggregateRoot --> repo
        serviceBus.broadcast(events, "passenger_aggregate_exchange");
    }
}
