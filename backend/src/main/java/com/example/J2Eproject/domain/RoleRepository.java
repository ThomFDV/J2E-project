package com.example.J2Eproject.domain;

import com.example.J2Eproject.domain.models.Role;
import com.example.J2Eproject.domain.models.enums.ERole;

public interface RoleRepository {
    Role getByName(ERole name);
}
