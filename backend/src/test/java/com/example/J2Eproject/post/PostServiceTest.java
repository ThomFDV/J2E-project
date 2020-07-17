package com.example.J2Eproject.post;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    public void saved_post_should_not_be_null() {
        Post post = new Post("Posting a test", "Posting for testing the application", "test",
                "https://gph.is/g/46qnd88");
        when(postRepository.save(any(Post.class))).then(returnsFirstArg());
        Post savedPost = postService.add(post.getTitle(), post.getContent(), post.getAuthor(), post.getGifUrl());
        assertThat(savedPost).isNotNull();
    }

    @Test
    public void saved_post_should_be_equals_to_given_post() {
        Post post = new Post("Posting a test", "Posting for testing the application", "test",
                "https://gph.is/g/46qnd88");
        when(postRepository.save(any(Post.class))).then(returnsFirstArg());
        Post savedPost = postService.add(post.getTitle(), post.getContent(), post.getAuthor(), post.getGifUrl());
        assertThat(savedPost.getTitle()).isEqualTo(post.getTitle());
    }
}
