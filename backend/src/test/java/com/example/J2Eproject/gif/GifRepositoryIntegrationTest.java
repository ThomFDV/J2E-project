package com.example.J2Eproject.gif;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

public class GifRepositoryIntegrationTest {

    @Autowired GifRepository gifRepository;
    @Autowired MongoOperations mongoOperations;

    @Before
    public void reset() {

    }

}
