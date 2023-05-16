package com.suryansh.Repository;

import com.suryansh.Entity.Comments;
import com.suryansh.Entity.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
class CommentsRepositoryTest {
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private MovieRepository movieRepository;
    Comments comments;
    Movie movie;

    @BeforeEach
    void setUpMovie() {
        movie = Movie.builder()
                .id(1)
                .genres(null)
                .productionCompanies(null)
                .spokenLanguages(null)
                .title("Shazam Fury of gods")
                .movieCast(null)
                .comments(null)
                .build();
        movieRepository.save(movie);
        comments = new Comments(1L,
                "Suryansh Sharma"
                ,"","Amazing movie", Instant.now(),movie);
        commentsRepository.save(comments);
    }
    @AfterEach
    void tearDown() {
        comments=null;
        commentsRepository.deleteAll();
        movie=null;
        movieRepository.deleteAll();
    }

    @Test
    @Transactional
    void testGetAllCommentsByMovieId() {
        Pageable pageable = PageRequest.of(0,6);
        Page<Comments>commentsPage = commentsRepository
                .getAllCommentsByMovieId(1,pageable);
        assertThat(commentsPage.getContent().get(0).getId())
                .isEqualTo(comments.getId());
    }

    @Test
    void testGetAllCommentsByMovieId_MovieIdNotExist() {
        Pageable pageable = PageRequest.of(0,6);
        Page<Comments>commentsPage = commentsRepository
                .getAllCommentsByMovieId(111,pageable);
        assertThat(commentsPage.getContent())
                .isEmpty();
    }

}