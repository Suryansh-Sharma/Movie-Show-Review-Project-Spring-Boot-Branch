package com.suryansh.Repository;

import com.suryansh.Entity.Movie;
import com.suryansh.Entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Optional<Movie> findByTitle(String title);

    @Query("select m from Movie m join m.movieCast c where c.person = :person")
    Page<Movie> findByMovieCastPerson(@Param("person") Person person, Pageable pageable);

    @Query("select m from Movie m where lower(m.title) like lower(concat('%',:name,'%') )")
    List<Movie> searchMovieByTitle(String name, Pageable pageable);

    @Query("select m from Movie m order by m.popularity desc ")
    Page<Movie> getTopMovies(Pageable pageable);

    @Query("select m from Movie m join m.movieCast c where c.person = :person")
    Page<Movie> getTopByPerson(Person person, Pageable pageable);

    @Query("select m from Movie m order by m.voteAverage desc")
    Page<Movie> getTopTrendingMovie(Pageable pageable);
}
