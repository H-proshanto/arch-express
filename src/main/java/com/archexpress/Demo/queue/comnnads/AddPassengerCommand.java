package com.archexpress.Demo.queue.comnnads;

import com.archexpress.Demo.passenger.database.Passenger;
import com.archexpress.Demo.queue.Publishable;

public class AddPassengerCommand implements Command, Publishable {
    private Passenger passenger;

    public Passenger getPassenger() {
        return passenger;
    }
}
