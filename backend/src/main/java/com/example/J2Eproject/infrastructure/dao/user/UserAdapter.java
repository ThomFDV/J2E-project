package com.example.J2Eproject.infrastructure.dao.user;

import com.example.J2Eproject.domain.models.User;

public class UserAdapter {

    public static User convertToUser(MongoUser mongoUser) {
        return new User(mongoUser.getEmail(), mongoUser.getUsername(), mongoUser.getFirstName(),
                mongoUser.getLastName(), mongoUser.getPassword());
    }

    public static MongoUser convertToMongoUser(User user) {
        return new MongoUser(user.getEmail(), user.getUsername(), user.getFirstName(),
                user.getLastName(), user.getPassword());
    }
}
