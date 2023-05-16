package com.suryansh.Service;

import com.suryansh.Model.CastModel;
import com.suryansh.Model.CommentModel;
import com.suryansh.Model.MovieModel;
import com.suryansh.Model.ReviewModel;
import com.suryansh.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
    String saveMovie(MovieModel model);

    MovieFullDetailDto getMovieFullDetailByName(String name);

    MovieFullDetailDto getMovieFullDetailById(int id);

    MoviePagingDto getAllShows(Pageable pageable);

    MoviePagingDto getByGenre(String genre, Pageable pageable);

    void saveCast(List<CastModel> castModel, int movieId);

    MovieCredits getCreditsOfMovie(int id);

    MoviePagingDto getMovieByPerson(String name, Pageable pageable);

    SearchMovieDto searchMovie(String name);

    CommentPageDto getShowComments(int id, Pageable pageable);

    void addNewComment(CommentModel commentModel);

    MoviePagingDto getMoviesByProduction(String name, Pageable pageable);

    MoviePagingDto getRandomShowsByType(String type, String typeName, Pageable pageable);

    void addNewReviewOfMovie(ReviewModel reviewModel, int movieId);

    boolean isMovieReviewedByUser(int movieId);

    ReviewDto getUserReviewForMovie(int movieId);

    void updateReviewForUser(ReviewModel reviewModel, Long reviewId);
}
