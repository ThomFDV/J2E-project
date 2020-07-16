package com.example.J2Eproject.infrastructure.persistence.dal;

import com.example.J2Eproject.infrastructure.persistence.entities.Gif;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "gif", path = "gif")
public interface GifRepository extends MongoRepository<Gif, String> {
    @Query("{url: '?1', name: '?0'}")
    Optional<Gif> findByNameAndUrl(String name, String url);
}
