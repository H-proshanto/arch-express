package com.archexpress.Demo.aggregateRoots;

import com.archexpress.Demo.passenger.database.Passenger;
import com.archexpress.Demo.queue.comnnads.AddPassengerCommand;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "passengers_ar")
public class PassengerAggregateRoot {
    @Id
    private ObjectId id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String identificationType;
    private String identificationNumber;
    private List<String> identificationDocuments;
    private String presentAddress;
    private String permanentAddress;
    private String status;
    private Double rating;
    private String referenceId;
    private Long createdAt;
    private Long updatedAt;
    private Long deletedAt;

    public void create(AddPassengerCommand passenger, List<Event> events){
//        validation -->
//        if (passenger.getPassenger().getFirstName() == null || passenger.getPassenger().getLastName() == "")
//            System.out.println("");
//      populate values for this
        PassangerCreatedEvent createdEvent = new PassangerCreatedEvent();
        createdEvent.name = this.firstName + " " + this.lastName;
        events.add(createdEvent);
    }
}
