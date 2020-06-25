package com.example.J2Eproject.gif;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Import({
        GifService.class,
        GifRepository.class
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GifServiceTests {

    @Autowired
    private GifService sut;

    @Test
    void should_create_gif() {
        var gif = sut.add("Test", "http://test.com");
        assertThat(sut.getById(gif.get_id().toString()).isPresent());
    }

    @Test
    void should_can_add_gif() {
        var gif = new GifDTO("Test1", "http://test.com");
        assertThat(sut.canAdd(gif)).isEqualTo(true);
    }

    @Test
    void should_can_not_add_gif() {
        var gif = new GifDTO("Test1", "http://test.com");
        assertThat(sut.canAdd(gif)).isEqualTo(false);
    }
}