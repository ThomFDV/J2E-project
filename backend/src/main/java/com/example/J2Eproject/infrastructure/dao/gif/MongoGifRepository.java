package com.example.J2Eproject.infrastructure.dao.gif;

import com.example.J2Eproject.domain.GifRepository;
import com.example.J2Eproject.domain.models.Gif;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
class MongoGifRepository implements GifRepository {

    private final MongoTemplate mongoTemplate;

    public MongoGifRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Gif getByNameAndUrl(String name, String url) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        query.addCriteria(Criteria.where("url").is(url));
        MongoGif mongoGif = mongoTemplate.findOne(query, MongoGif.class);
        if (mongoGif == null) {
            return null;
        }
        return GifAdapter.convertToGif(mongoGif);
    }

    @Override
    public Gif add(Gif gif) {
        final MongoGif mongoGif = GifAdapter.convertToMongoGif(gif);
        final MongoGif savedMongoGif = mongoTemplate.save(mongoGif);
        return GifAdapter.convertToGif(savedMongoGif);
    }

    @Override
    public Gif getById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        MongoGif mongoGif = mongoTemplate.findOne(query, MongoGif.class);
        if (mongoGif == null) {
            return null;
        }
        return GifAdapter.convertToGif(mongoGif);
    }
}
