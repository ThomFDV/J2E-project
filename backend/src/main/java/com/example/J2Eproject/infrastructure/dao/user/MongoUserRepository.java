package com.example.J2Eproject.infrastructure.dao.user;

import com.example.J2Eproject.domain.UserRepository;
import com.example.J2Eproject.domain.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MongoUserRepository implements UserRepository {

    private final MongoTemplate mongoTemplate;

    public MongoUserRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User add(User user) {
        final MongoUser mongoUser = UserAdapter.convertToMongoUser(user);
        final MongoUser savedMongoUser = mongoTemplate.save(mongoUser);
        return UserAdapter.convertToUser(savedMongoUser);
    }

    @Override
    public User getByUsername(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        final MongoUser mongoUser = mongoTemplate.findOne(query, MongoUser.class);
        if (mongoUser == null) {
            return null;
        }
        return UserAdapter.convertToUser(mongoUser);
    }

    @Override
    public User getById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        final MongoUser mongoUser = mongoTemplate.findOne(query, MongoUser.class);
        if (mongoUser == null) {
            return null;
        }
        return UserAdapter.convertToUser(mongoUser);
    }

    @Override
    public List<User> findAll() {
        final List<MongoUser> mongoUser = mongoTemplate.findAll(MongoUser.class);
        final List<User> user = mongoUser.stream().map(UserAdapter::convertToUser).collect(Collectors.toList());

        return user;
    }

    @Override
    public Boolean existsByUsername(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        return mongoTemplate.exists(query, MongoUser.class);
    }

    @Override
    public Boolean existsById(ObjectId id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.exists(query, MongoUser.class);
    }

    @Override
    public Boolean existsByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.exists(query, MongoUser.class);
    }
}
