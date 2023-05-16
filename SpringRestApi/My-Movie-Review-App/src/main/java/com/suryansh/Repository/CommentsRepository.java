package com.suryansh.Repository;

import com.suryansh.Entity.Comments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {
    @Query("select c from Comments c where c.movie.id = :movieId")
    Page<Comments> getAllCommentsByMovieId(@Param("movieId") int movieId, Pageable pageable);
}
