package com.suryansh.Controller;

import com.suryansh.Model.CastModel;
import com.suryansh.Model.CommentModel;
import com.suryansh.Model.MovieModel;
import com.suryansh.Model.ReviewModel;
import com.suryansh.Service.MovieService;
import com.suryansh.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/movie")
@CrossOrigin("*")
public class ShowController {
    private final MovieService movieService;
    @Value("${jpaPaginationSize}")
    private int pageSize;

    public ShowController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/add")
    public String addNewShow(@RequestBody @Valid MovieModel model) {
        return movieService.saveMovie(model);
    }

    // Get All Movie Name for Search bar .
    @GetMapping("/search-by-name/{name}")
    public SearchMovieDto searchMoviesByName(@PathVariable String name) {
        return movieService.searchMovie(name);
    }

    // Get Full show  by their Name.
    @GetMapping("/full-detail-by-name/{name}")
    public MovieFullDetailDto getShowFullDetailByName(@PathVariable String name) {
        return movieService.getMovieFullDetailByName(name);
    }

    // Get Ful Detail By id
    @GetMapping("/full-detail-by-id/{id}")
    public MovieFullDetailDto getShowFullDetailById(@PathVariable int id) {
        return movieService.getMovieFullDetailById(id);
    }

    // Get All Shows with Paging.
    @GetMapping("/all")
    public MoviePagingDto all(@RequestParam(defaultValue = "0", required = false) int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return movieService.getAllShows(pageable);
    }

    // Get Random Top shows by Random ,Trending , Genres , Person.
    @GetMapping("/get-random-by-various-type/{type}/{type_name}")
    public MoviePagingDto getMovieByVariousType(@PathVariable String type, @PathVariable String type_name, @RequestParam(defaultValue = "0", required = false) int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return movieService.getRandomShowsByType(type, type_name, pageable);
    }

    //  Get Shows By their Genre.
    @GetMapping("/by-genre/{genre}")
    public MoviePagingDto movieByGenre(@PathVariable String genre, @RequestParam(defaultValue = "0", required = false) int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return movieService.getByGenre(genre, pageable);
    }

    @PostMapping("/add-cast/{movieId}")
    public void addCast(@RequestBody List<CastModel> castModel, @PathVariable int movieId) {
        movieService.saveCast(castModel, movieId);
    }

    @GetMapping("/credits-by-movie-id/{id}")
    public MovieCredits getMovieCredits(@PathVariable int id) {
        return movieService.getCreditsOfMovie(id);
    }

    // Get Shows By Person Name.
    @GetMapping("/by-person-name/{name}")
    public MoviePagingDto getMovieByPerson(@PathVariable String name, @RequestParam(defaultValue = "0", required = false) int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return movieService.getMovieByPerson(name, pageable);
    }

    // Get Movie by Production Company
    @GetMapping("/by-production/{name}")
    public MoviePagingDto getMovieByProduction(@PathVariable String name, @RequestParam(defaultValue = "0", required = false) int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return movieService.getMoviesByProduction(name, pageable);
    }

    // Add New Review For Movie
    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/add-review/{movieId}")
    private void addNewReview(@Valid @RequestBody ReviewModel reviewModel, @PathVariable int movieId) {
        movieService.addNewReviewOfMovie(reviewModel, movieId);
    }

    // Only Login User Can Post Comments on Show.
    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/add-new-comment")
    public void uploadComment(@RequestBody CommentModel commentModel) {
        movieService.addNewComment(commentModel);
    }

    // Add New Comment for Movie.
    @GetMapping("/comments-by-movie-id/{id}")
    public CommentPageDto getShowComments(@PathVariable int id, @RequestParam(defaultValue = "0", required = false) int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return movieService.getShowComments(id, pageable);
    }

    // Check is Movie already reviewed by user
    @GetMapping("/check-reviewed-by-user/{movieId}")
    public boolean checkIsReviewedByUser(@PathVariable int movieId) {
        return movieService.isMovieReviewedByUser(movieId);
    }

    // Get Review for Movie of user.
    @GetMapping("/get-review-for-user/{movieId}")
    public ReviewDto getReviewForUser(@PathVariable int movieId) {
        return movieService.getUserReviewForMovie(movieId);
    }

    // Update Review of user .
    @PostMapping("/update-review-for-user/{reviewId}")
    public void updateReview(@Valid @RequestBody ReviewModel reviewModel, @PathVariable Long reviewId) {
        movieService.updateReviewForUser(reviewModel, reviewId);
    }

}
