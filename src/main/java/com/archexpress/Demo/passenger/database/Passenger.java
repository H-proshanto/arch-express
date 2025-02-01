package com.archexpress.Demo.passenger.database;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(collection = "passengers")
public class Passenger implements Serializable {
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
}
