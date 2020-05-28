package com.example.J2Eproject.user;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Accessors(chain = true)
public class User {
//    @Id
//    private ObjectId id;

    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private List<String> roles;
}
