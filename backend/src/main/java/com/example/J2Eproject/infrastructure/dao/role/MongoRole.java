package com.example.J2Eproject.infrastructure.dao.role;

import com.example.J2Eproject.domain.models.enums.ERole;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class MongoRole {
    @Id
    private String id;

    private ERole name;

    public MongoRole() {
    }

    public MongoRole(String id, ERole name) {
        this.id = id;
        this.name = name;
    }

    public MongoRole(ERole name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}
