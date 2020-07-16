package com.example.J2Eproject.infrastructure.dao.role;

import com.example.J2Eproject.domain.models.Role;

public class RoleAdapter {
    public static Role convertToRole(MongoRole mongoRole) {
        return new Role(mongoRole.getId(), mongoRole.getName());
    }

    public static MongoRole convertToMongoRole(Role role) {
        return new MongoRole(role.getId(), role.getName());
    }
}
