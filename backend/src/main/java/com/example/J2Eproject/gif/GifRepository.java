package com.example.J2Eproject.gif;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "gif", path = "gif")
public interface GifRepository extends MongoRepository<Gif, String> {

}
