package com.archexpress.Demo.driver;

import com.archexpress.Demo.driver.database.Driver;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DriverRepository extends MongoRepository<Driver, ObjectId> {
}
