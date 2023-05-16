package com.suryansh.Repository;

import com.suryansh.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.movie.id = :movieId and r.user.id = :userId ")
    Review getReviewForUser(int movieId, Long userId);
}