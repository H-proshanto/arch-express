package com.archexpress.Demo.driver;

import com.archexpress.Demo.aggregateRoots.DriverAggregateRoot;
import com.archexpress.Demo.driver.database.Driver;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends MongoRepository<DriverAggregateRoot, ObjectId> {
}
