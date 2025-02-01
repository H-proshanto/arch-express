package com.archexpress.Demo.aggregateRoots;

import com.archexpress.Demo.passenger.database.Passenger;
import com.archexpress.Demo.queue.comnnads.AddPassengerCommand;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "passengers_ar")
public class PassengerAggregateRoot {
    @Id
    private String id;
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

    public void create(AddPassengerCommand command, List<Event> events){
        this.id = UUID.randomUUID().toString();
        this.firstName = command.getPassenger().getFirstName();
        this.lastName = command.getPassenger().getLastName();
        this.email = command.getPassenger().getEmail();
        this.phone = command.getPassenger().getPhone();
        this.identificationType = command.getPassenger().getIdentificationType();
        this.identificationNumber = command.getPassenger().getIdentificationNumber();
        this.identificationDocuments = command.getPassenger().getIdentificationDocuments();
        this.presentAddress = command.getPassenger().getPresentAddress();
        this.permanentAddress = command.getPassenger().getPermanentAddress();
        this.status = command.getPassenger().getStatus();
        this.rating = command.getPassenger().getRating();
        this.referenceId = command.getPassenger().getReferenceId();
        this.createdAt = command.getPassenger().getCreatedAt();
//        validation -->
//        if (passenger.getPassenger().getFirstName() == null || passenger.getPassenger().getLastName() == "")
//            System.out.println("");
//      populate values for this
        PassangerCreatedEvent createdEvent = new PassangerCreatedEvent();
        createdEvent.setId(this.id);
        createdEvent.setFirstName(this.firstName);
        createdEvent.setLastName(this.lastName);
        createdEvent.setEmail(this.email);
        createdEvent.setPhone(this.phone);
        createdEvent.setStatus(this.status);
        createdEvent.setRating(this.rating);
        createdEvent.setCreatedAt(this.createdAt);
        createdEvent.setReferenceId(this.referenceId);
        events.add(createdEvent);
    }
}
