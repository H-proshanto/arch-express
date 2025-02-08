package com.archexpress.Demo.queue.comnnads;

import com.archexpress.Demo.driver.database.Driver;
import com.archexpress.Demo.passenger.database.Passenger;
import com.archexpress.Demo.queue.Publishable;

public class AddDriverCommand implements Command, Publishable {
    private Driver driver;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
