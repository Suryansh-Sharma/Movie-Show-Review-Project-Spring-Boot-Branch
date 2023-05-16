package com.suryansh.Repository;

import com.suryansh.Entity.Genre;
import com.suryansh.Entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Genre findByName(String name);

    @Query("select m from Movie m join m.genres g where g.name = :name")
    Page<Movie> findMovieByGenre(@Param("name") String genre, Pageable pageable);

    @Query("select m from Movie m join m.genres g where g.name = :typeName order by m.popularity desc ")
    Page<Movie> getTopMoviesByGenre(String typeName, Pageable pageable);
}