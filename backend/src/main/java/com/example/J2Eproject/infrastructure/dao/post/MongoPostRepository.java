package com.example.J2Eproject.infrastructure.dao.post;

import com.example.J2Eproject.domain.PostRepository;
import com.example.J2Eproject.domain.models.Post;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MongoPostRepository implements PostRepository {

    private final MongoTemplate mongoTemplate;

    public MongoPostRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Post byId(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        final MongoPost mongoPost = mongoTemplate.findOne(query, MongoPost.class);
        if (mongoPost == null) {
            return null;
        }
        return PostAdapter.toPost(mongoPost);
    }

    @Override
    public List<Post> getAll() {
        final List<MongoPost> mongoPosts = mongoTemplate.findAll(MongoPost.class);
        return mongoPosts.stream().map(PostAdapter::toPost).collect(Collectors.toList());
    }

    @Override
    public Post add(Post post) {
        MongoPost mongoPost = PostAdapter.toMongoPost(post);
        MongoPost savedMongoPost = mongoTemplate.save(mongoPost);
        return PostAdapter.toPost(savedMongoPost);
    }

    @Override
    public void deleteById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.remove(query, MongoPost.class);
    }
}
