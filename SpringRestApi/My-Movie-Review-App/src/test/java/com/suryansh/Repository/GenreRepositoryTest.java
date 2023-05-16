package com.suryansh.Repository;

import com.suryansh.Entity.Genre;
import com.suryansh.Entity.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@DataJpaTest
class GenreRepositoryTest {
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private MovieRepository movieRepository;
    Movie movie;
    List<Genre>genreList;

    @BeforeEach
    void setUp() {
        Genre genre1 = Genre.builder().name("Action").build();
        Genre genre2 = Genre.builder().name("Drama").build();
        Genre genre3 = Genre.builder().name("Comedy").build();
        genreList = List.of(genre1,genre2,genre3);
        genreRepository.saveAll(genreList);
        var allGenres = genreRepository.findAll();
        movie = Movie.builder()
                .id(1)
                .genres(allGenres)
                .productionCompanies(null)
                .spokenLanguages(null)
                .title("Shazam Fury of gods")
                .movieCast(null)
                .comments(null)
                .build();
        movieRepository.save(movie);
    }

    @AfterEach
    void tearDown() {
        movie=null;
        genreList=null;
        movieRepository.deleteAll();
        genreRepository.deleteAll();
    }
    @Test
    void testFindByName(){
        var genre = genreRepository.findByName("Action");
        assertThat(genre.getName())
                .isEqualTo(genreList.get(0).getName());
    }
    @Test
    void testFindMovieByGenre_Present() {
        Pageable pageable = PageRequest.of(0,6);
        Page<Movie>moviePage = genreRepository.findMovieByGenre("Action",pageable);
        assertThat(moviePage.getContent().get(0).getGenres().stream().toList())
                .isEqualTo(genreList);
    }

    @Test
    void testFindMovieByGenre_NotPresent() {
        Pageable pageable = PageRequest.of(0,6);
        Page<Movie>moviePage = genreRepository.findMovieByGenre("Horror",pageable);
        Assertions.assertThat(moviePage.getContent())
                .isEmpty();
    }
}