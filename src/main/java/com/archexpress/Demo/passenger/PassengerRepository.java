package com.archexpress.Demo.passenger;

import com.archexpress.Demo.aggregateRoots.PassengerAggregateRoot;
import com.archexpress.Demo.passenger.database.Passenger;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends MongoRepository<PassengerAggregateRoot, ObjectId> {
}
