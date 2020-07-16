package com.example.J2Eproject.infrastructure.dao.role;

import com.example.J2Eproject.domain.RoleRepository;
import com.example.J2Eproject.domain.models.Role;
import com.example.J2Eproject.domain.models.enums.ERole;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Query;


@Repository
public class MongoRoleRepository implements RoleRepository {

    private final MongoTemplate mongoTemplate;

    public MongoRoleRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Role getByName(ERole name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        MongoRole mongoRole = mongoTemplate.findOne(query, MongoRole.class);
        if (mongoRole == null) {
            return null;
        }
        return RoleAdapter.convertToRole(mongoRole);

    }
}
