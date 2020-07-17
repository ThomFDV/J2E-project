package com.example.J2Eproject.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostServiceIntegrationTest {

    @Autowired
    private PostService postService;

    @Test
    public void should_create_post() {
        Post postReturned = postService.add("Post for test purpose",
                "This is the content of the post", "", "test");

        assertThat(postReturned).isNotNull();
    }

    @Test
    public void should_should_create_post_and_be_equals() {
        Post postReturned = postService.add("Post for test purpose",
                "This is the content of the post", "", "test");

        assertThat(postReturned.getTitle()).isEqualTo("Post for test purpose");
    }
}
