package com.archexpress.Demo.aggregateRoots;

import com.archexpress.Demo.queue.comnnads.AddDriverCommand;
import com.archexpress.Demo.queue.comnnads.AddPassengerCommand;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "drivers")
public class DriverAggregateRoot {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String identificationNumber;
    private String identificationType;
    private List<String> identificationDocuments;
    private String presentAddress;
    private String permanentAddress;
    private String trainingStatus;
    private String licenseType;
    private String vehicleType;
    private String vehicleFitnessStatus;
    private String status;
    private double rating;
    private Boolean activeStatus;
    private List<String> licenseDocuments;
    private Long createdAt;
    private Long updatedAt;
    private Long deletedAt;

    public void create(AddDriverCommand command, List<Event> events){
        this.id = UUID.randomUUID().toString();
        this.firstName = command.getDriver().getFirstName();
        this.lastName = command.getDriver().getLastName();
        this.email = command.getDriver().getEmail();
        this.phone = command.getDriver().getPhone();
        this.identificationType = command.getDriver().getIdentificationType();
        this.identificationNumber = command.getDriver().getIdentificationNumber();
        this.identificationDocuments = command.getDriver().getIdentificationDocuments();
        this.presentAddress = command.getDriver().getPresentAddress();
        this.permanentAddress = command.getDriver().getPermanentAddress();
        this.status = command.getDriver().getStatus();
        this.rating = command.getDriver().getRating();
//        validation -->
//        if (passenger.getDriver().getFirstName() == null || passenger.getDriver().getLastName() == "")
//            System.out.println("");
//      populate values for this
        DriverCreatedEvent createdEvent = new DriverCreatedEvent();
        createdEvent.setId(this.id);
        createdEvent.setFirstName(this.firstName);
        createdEvent.setLastName(this.lastName);
        createdEvent.setEmail(this.email);
        createdEvent.setPhone(this.phone);
        createdEvent.setStatus(this.status);
        createdEvent.setRating(this.rating);
        events.add(createdEvent);
    }
}
