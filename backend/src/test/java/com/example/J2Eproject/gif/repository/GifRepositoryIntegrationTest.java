package com.example.J2Eproject.gif.repository;

import com.example.J2Eproject.gif.Gif;
import com.example.J2Eproject.gif.GifRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GifRepositoryIntegrationTest {

    @Autowired
    GifRepository gifRepository;
    @Autowired MongoTemplate mongoTemplate;

    @Before
    public void before() {
        gifRepository.save(new Gif("http://test.test", "Test", 1));
    }

    @Test
    public void should_find_gif_by_name_and_url() {
        var gif = gifRepository.findByNameAndUrl("Test", "http://test.test");
        assertThat(gif).isNotEmpty();
    }

    @Test
    public void should_not_find_by_name_and_url() {
        var gif = gifRepository.findByNameAndUrl("Test1", "http://test.test");
        assertThat(gif).isEmpty();
    }

}
