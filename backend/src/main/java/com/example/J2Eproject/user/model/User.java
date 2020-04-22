package com.example.J2Eproject.user.model;

import lombok.Data;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document(collection = "user")
public class User {
    @Id
    public ObjectId id;

    public String firstName;
    public String lastName;
}
