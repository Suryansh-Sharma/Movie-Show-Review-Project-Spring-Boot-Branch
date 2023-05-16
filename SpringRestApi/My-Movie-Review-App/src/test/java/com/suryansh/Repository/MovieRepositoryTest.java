package com.suryansh.Repository;

import com.suryansh.Entity.Cast;
import com.suryansh.Entity.Movie;
import com.suryansh.Entity.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class MovieRepositoryTest {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CastRepository castRepository;
    Movie movie;
    List<Cast>castList;
    List<Person>personList;

    @BeforeEach
    void setUp() {
        var person1 = Person.builder().name("Zachary Levi").build();
        var person2 = Person.builder().name("Asher Angel").build();
        var person3 = Person.builder().name("Jack Dylan Grazer").build();

        var cast1 = Cast.builder().name("Zachary Levi").profilePath("").character("Shazam").person(person1).build();
        var cast2 = Cast.builder().name("Asher Angel").profilePath("").character("Billy Watson").person(person2).build();
        var cast3 = Cast.builder().name("Jack Dylan Grazer").profilePath("").character("Freedy").person(person3).build();
        castList=List.of(cast1,cast2,cast3);
        personList=List.of(person1,person2,person3);
        movie = Movie.builder()
                .id(1)
                .genres(null)
                .productionCompanies(null)
                .spokenLanguages(null)
                .title("Shazam Fury of gods")
                .movieCast(castList)
                .comments(null)
                .build();
        personRepository.saveAll(personList);
        castRepository.saveAll(castList);
        movieRepository.save(movie);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testFindByTitle_Present() {
        var movieFromDb = movieRepository.findByTitle("Shazam Fury of gods");
        assertThat(movieFromDb.isPresent()).isTrue();
    }
    @Test
    void testFindByTitle_NotPresent(){
        var movieFromDb = movieRepository.findByTitle("Shazam $#@2");
        assertThat(movieFromDb.isPresent()).isFalse();
    }

    @Test
    void findByMovieCastPerson_Present() {
        var personFromDb = personRepository.findByName("Zachary Levi");
        Pageable pageable = PageRequest.of(0,6);
        var moviePage = movieRepository.findByMovieCastPerson(personFromDb,pageable);
        assertThat(moviePage.getContent().get(0).getTitle())
                .isEqualTo(movie.getTitle());
    }
    @Test
    void findByMovieCastPerson_NotFound(){
        var personFromDb = personRepository.findByName("Not_Present");
        Pageable pageable = PageRequest.of(0,6);
        var moviePage = movieRepository.findByMovieCastPerson(personFromDb,pageable);
        assertThat(moviePage.getContent().size())
                .isEqualTo(0);
    }

    @Test
    void searchMovieByTitle_Present() {
        Pageable pageable = PageRequest.of(0,6);
        var movieList = movieRepository.searchMovieByTitle("Sh",pageable);
        assertThat(movieList.size())
                .isNotZero();
    }
    @Test
    void searchMovieByTitle_NotPresent() {
        Pageable pageable = PageRequest.of(0,6);
        var movieList = movieRepository.searchMovieByTitle("Not_Present",pageable);
        assertThat(movieList.size())
                .isEqualTo(0);
    }
}