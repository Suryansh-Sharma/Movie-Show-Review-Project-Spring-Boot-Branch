package com.suryansh.Service.impl;

import com.suryansh.Entity.*;
import com.suryansh.Exception.SpringShowException;
import com.suryansh.Model.CastModel;
import com.suryansh.Model.CommentModel;
import com.suryansh.Model.MovieModel;
import com.suryansh.Model.ReviewModel;
import com.suryansh.Repository.*;
import com.suryansh.Service.MovieService;
import com.suryansh.component.MovieMapper;
import com.suryansh.dto.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final ProductionCompanyRepository productionRepository;
    private final SpokenLanguageRepository spokenLanguageRepository;
    private final PersonRepository personRepository;
    private final MovieMapper movieMapper;
    private final CommentsRepository commentsRepository;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public String saveMovie(MovieModel model) {
        movieRepository.findByTitle(model.getTitle())
                .ifPresent(m -> {
                    throw new SpringShowException("Movie is already present !!");
                });

        if (model.getGenres() == null) {
            throw new SpringShowException("Genres is empty for movie");
        } else if (model.getProduction_companies() == null) {
            throw new SpringShowException("Production is empty for movie");
        } else if (model.getSpoken_languages() == null) {
            throw new SpringShowException("Spoken Language is empty for movie");
        }

        var collection = Collection.builder()
                .theMovieDbId(model.getBelongs_to_collection().getTheMovieDbId())
                .name(model.getBelongs_to_collection().getName())
                .backdropPath(model.getBelongs_to_collection().getBackdrop_path())
                .posterPath(model.getBelongs_to_collection().getPoster_path())
                .build();
        var genres = model.getGenres().stream()
                .map(genreModel -> {
                    var existingGenre = genreRepository.findByName(genreModel.getName());
                    return existingGenre != null ?
                            Genre.builder().id(existingGenre.getId()).name(existingGenre.getName()).build() :
                            Genre.builder().name(genreModel.getName()).build();
                })
                .toList();
        var production = model.getProduction_companies().stream()
                .map(productionModel -> {
                    var existingProduction = productionRepository.findByName(productionModel.getName());
                    return existingProduction != null ?
                            ProductionCompany.builder()
                                    .id(existingProduction.getId())
                                    .logoPath(existingProduction.getLogoPath())
                                    .name(existingProduction.getName())
                                    .originCountry(existingProduction.getOriginCountry())
                                    .build() :
                            ProductionCompany.builder()
                                    .logoPath(productionModel.getLogo_path())
                                    .name(productionModel.getName())
                                    .originCountry(productionModel.getOrigin_country())
                                    .build();
                })
                .toList();
        var spokenLanguage = model.getSpoken_languages().stream()
                .map(modelLang -> {
                    var existingLang = spokenLanguageRepository.findByEnglishName(modelLang.getEnglish_name());
                    return existingLang != null ?
                            SpokenLanguage.builder()
                                    .id(existingLang.getId())
                                    .englishName(existingLang.getEnglishName())
                                    .iso_639_1(existingLang.getIso_639_1())
                                    .name(existingLang.getName())
                                    .build() :
                            SpokenLanguage.builder()
                                    .englishName(modelLang.getEnglish_name())
                                    .iso_639_1(modelLang.getIso_639_1())
                                    .name(modelLang.getName())
                                    .build();
                })
                .toList();

        var movie = Movie.builder()
                .adult(model.getAdult())
                .backdropPath(model.getBackdrop_path())
                .belongsToCollection(collection)
                .budget(model.getBudget())
                .genres(genres)
                .homepage(model.getHomepage())
                .imdbId(model.getImdb_id())
                .originalLanguage(model.getOriginal_language())
                .originalTitle(model.getOriginal_title())
                .overview(model.getOverview())
                .popularity(model.getPopularity())
                .posterPath(model.getPoster_path())
                .productionCompanies(production)
                .releaseDate(model.getRelease_date())
                .revenue(model.getRevenue())
                .runtime(model.getRuntime())
                .spokenLanguages(spokenLanguage)
                .status(model.getStatus())
                .tagline(model.getTagline())
                .title(model.getTitle())
                .video(model.getVideo())
                .voteAverage(model.getVote_average())
                .voteCount(model.getVote_count())
                .movieCast(null)
                .comments(null)
                .build();
        try {
            genreRepository.saveAll(genres);
            productionRepository.saveAll(production);
            spokenLanguageRepository.saveAll(spokenLanguage);

            movieRepository.save(movie);

            logger.info("Movie is successfully saved !!");
            return "Movie is successfully saved !!";
        } catch (Exception e) {
            logger.error("Exception " + e);
            return "Unable to save Movie";
        }
    }

    @Override
    public MovieFullDetailDto getMovieFullDetailByName(String name) {
        var movie = movieRepository.findByTitle(name)
                .orElseThrow(() -> new SpringShowException("Unable to find movie of name " + name));
        return getMovieFullDetails(movie);
    }

    @Override
//    @Cacheable(value = "movie",key = "#id")
    public MovieFullDetailDto getMovieFullDetailById(int id) {
        var movie = movieRepository.findById(id)
                .orElseThrow(() -> new SpringShowException("Unable to find movie of id " + id));
        return getMovieFullDetails(movie);
    }

    @Override
    public MoviePagingDto getAllShows(Pageable pageable) {
        var moviesPage = movieRepository.findAll(pageable);
        var allMovies = moviesPage.getContent().stream()
                .map(movieMapper::movieEntityToDto)
                .toList();
        return new MoviePagingDto(pageable.getPageNumber() + 1, allMovies,
                moviesPage.getTotalPages(), moviesPage.getTotalElements());
    }

    @Override
    public MoviePagingDto getByGenre(String genre, Pageable pageable) {
        var moviesPage = genreRepository.findMovieByGenre(genre, pageable);
        var allMovies = moviesPage.getContent().stream()
                .map(movieMapper::movieEntityToDto)
                .toList();
        return new MoviePagingDto(pageable.getPageNumber() + 1, allMovies,
                moviesPage.getTotalPages(), moviesPage.getTotalElements());

    }

    @Override
    @Transactional
    public void saveCast(List<CastModel> castModel, int movieId) {
        var movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new SpringShowException("Unable to find movie of id " + movieId));
        var castEntities = new ArrayList<Cast>();

        for (var val : castModel) {
            var person = personRepository.findByName(val.getName());
            if (person == null) {
                // Create a new Person entity if one doesn't exist already
                person = Person.builder()
                        .name(val.getName())
                        .build();
                personRepository.save(person);
            }

            var cast = Cast.builder()
                    .name(val.getName())
                    .profilePath(val.getProfile_path())
                    .character(val.getCharacter())
                    .person(person)
                    .movie(movie)
                    .build();
            castEntities.add(cast);
        }
        movie.getMovieCast().addAll(castEntities);
        try {
            movieRepository.save(movie);
            logger.info("Cast saved successfully for movie ");
        } catch (Exception e) {
            logger.error("Unable to save cast " + e);
        }
    }

    @Override
    public MovieCredits getCreditsOfMovie(int movieId) {
        var movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new SpringShowException("Unable to find movie of id " + movieId));
        var cast = movie.getMovieCast().stream()
                .map(c -> CastDto.builder()
                        .name(c.getName())
                        .profilePath(c.getProfilePath())
                        .character(c.getCharacter())
                        .build())
                .toList();
        return new MovieCredits(movieId, cast);
    }

    @Override
    public MoviePagingDto getMovieByPerson(String name, Pageable pageable) {
        var person = personRepository.findByName(name);
        if (person == null) throw new SpringShowException("Unable to find person " + name);
        var moviePage = movieRepository.findByMovieCastPerson(person, pageable);
        var movieDto = moviePage.getContent().stream()
                .map(movieMapper::movieEntityToDto)
                .toList();
        return new MoviePagingDto(pageable.getPageNumber() + 1, movieDto, moviePage.getTotalPages(), moviePage.getTotalElements());
    }

    @Override
    public SearchMovieDto searchMovie(String name) {
        Pageable pageable = PageRequest.of(0, 10);
        List<Movie> movie = movieRepository.searchMovieByTitle(name, pageable);
        if (movie == null) {
            return new SearchMovieDto(name, null, 0);
        } else {
            List<SearchMovieDto.result> result = movie.stream()
                    .map(m -> new SearchMovieDto.result(m.getId(), m.getTitle(),
                            m.getGenres().stream().map(Genre::getName).toList()))
                    .toList();
            return new SearchMovieDto(name, result, result.size());
        }
    }

    @Override
    public CommentPageDto getShowComments(int movieId, Pageable pageable) {
        var commentsPage = commentsRepository.getAllCommentsByMovieId(movieId, pageable);
        var commentRes = commentsPage.getContent().stream()
                .map(movieMapper::commentEntityToDto)
                .toList();
        return new CommentPageDto(pageable.getPageNumber() + 1, commentRes,
                commentsPage.getTotalPages(), commentsPage.getTotalElements());
    }

    @Override
    @Transactional
    public void addNewComment(CommentModel commentModel) {
        var movie = movieRepository.findById(commentModel.getMovieId())
                .orElseThrow(() -> new SpringShowException("Unable to find movie of id " + commentModel.getMovieId()));
        var user = userRepository.findByUsername(getCurrentUser())
                .orElseThrow(() -> new SpringShowException("Unable to find user !!"));
        Comments comments = Comments.builder()
                .userName(user.getUsername())
                .userPic(user.getUserPic())
                .note(commentModel.getData())
                .dateOfComment(Instant.now())
                .movie(movie)
                .build();
        try {
            commentsRepository.save(comments);
            logger.info("Comment is successfully added by {} for movie {} ", user.getUsername(), movie.getTitle());
        } catch (Exception e) {
            logger.error("Unable to save Comment " + e);
        }
    }

    @Override
    public MoviePagingDto getMoviesByProduction(String name, Pageable pageable) {
        var moviePage = productionRepository.getMovieByProductionName(name, pageable);
        var res = moviePage.getContent().stream()
                .map(movieMapper::movieEntityToDto)
                .toList();
        return new MoviePagingDto(pageable.getPageNumber(), res, moviePage.getTotalPages(), moviePage.getTotalElements());
    }

    @Override
    public MoviePagingDto getRandomShowsByType(String type, String typeName, Pageable pageable) {
        Page<Movie> moviePage;
        switch (type) {
            case "Genres" -> moviePage = genreRepository.getTopMoviesByGenre(typeName, pageable);
            case "Random" -> moviePage = movieRepository.getTopMovies(pageable);
            case "TopVoted" -> moviePage = movieRepository.getTopTrendingMovie(pageable);
            case "Person" -> {
                Person person = personRepository.findByName(typeName);
                if (person == null) throw new SpringShowException("Unable to find person " + typeName);
                moviePage = movieRepository.getTopByPerson(person, pageable);
            }
            default -> throw new SpringShowException("Unable to find type " + type);
        }
        if (moviePage == null) throw new SpringShowException("Unable to find movies");
        var res = new ArrayList<>(moviePage.getContent().stream()
                .map(movieMapper::movieEntityToDto)
                .toList());
        Collections.shuffle(res);
        return new MoviePagingDto(pageable.getPageNumber(), res, moviePage.getTotalPages(), moviePage.getTotalElements());
    }

    @Override
    @Transactional
    public void addNewReviewOfMovie(ReviewModel reviewModel, int movieId) {
        var movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new SpringShowException("Unable to find movie of id " + movieId));
        var user = userRepository.findByUsername(getCurrentUser())
                .orElseThrow(() -> new SpringShowException("Unable to find user " + getCurrentUser()));
        var review = Review.builder()
                .movieRating(reviewModel.getMovieRating())
                .bestActor(reviewModel.getBestActor())
                .plotRating(reviewModel.getPlotRating())
                .visualRatings(reviewModel.getVisualRatings())
                .soundRating(reviewModel.getSoundRating())
                .recommend(reviewModel.getRecommend())
                .otherComments(reviewModel.getOtherComments())
                .user(user)
                .movie(movie)
                .build();
        // Calculate new voteAverage and voteCount
        double userRating = reviewModel.getMovieRating(); // User's rating for the movie
        double existingVoteAverage = movie.getVoteAverage();
        int existingVoteCount = movie.getVoteCount();
        double newVoteAverage = ((existingVoteAverage * existingVoteCount) + userRating) / (existingVoteCount + 1);
        int newVoteCount = existingVoteCount + 1;

        // Update movie entity with new ratings
        movie.setVoteAverage(Math.round(newVoteAverage * 10) / 10.0);
        movie.setVoteCount(newVoteCount);
        try {
            movieRepository.save(movie);
            reviewRepository.save(review);
            logger.info("New Review is added for Movie {} by user {} ", movie.getOriginalTitle(), user.getUsername());
        } catch (Exception e) {
            logger.error("Unable to add review for Movie {} by user {} ", movie.getOriginalTitle(), user.getUsername() + e);
            throw new SpringShowException("Unable to save you review ");
        }
    }

    @Override
    public boolean isMovieReviewedByUser(int movieId) {
        var user = userRepository.findByUsername(getCurrentUser())
                .orElseThrow(() -> new SpringShowException("Unable to find user " + getCurrentUser()));
        if (user.getReviews() != null) {
            return user.getReviews().stream()
                    .anyMatch(review -> review.getMovie().getId() == movieId);
        }
        return false;
    }

    @Override
    public ReviewDto getUserReviewForMovie(int movieId) {
        var user = userRepository.findByUsername(getCurrentUser())
                .orElseThrow(() -> new SpringShowException("Unable to find user " + getCurrentUser()));
        Review review = reviewRepository.getReviewForUser(movieId, user.getId());
        return new ReviewDto(review.getId(), review.getMovieRating(), review.getBestActor(), review.getPlotRating(),
                review.getVisualRatings(), review.getSoundRating(), review.getRecommend(), review.getOtherComments());
    }

    @Override
    @Transactional
    public void updateReviewForUser(ReviewModel reviewModel, Long reviewId) {
        var user = userRepository.findByUsername(getCurrentUser())
                .orElseThrow(() -> new SpringShowException("Unable to find user " + getCurrentUser()));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new SpringShowException("Unable to find review of review " + reviewId));
        var movie = review.getMovie();
        var newReview = Review.builder()
                .id(review.getId())
                .movieRating(reviewModel.getMovieRating())
                .bestActor(reviewModel.getBestActor())
                .plotRating(reviewModel.getPlotRating())
                .visualRatings(reviewModel.getVisualRatings())
                .soundRating(reviewModel.getSoundRating())
                .recommend(reviewModel.getRecommend())
                .otherComments(reviewModel.getOtherComments())
                .user(user)
                .movie(movie)
                .build();
        try {
            reviewRepository.save(newReview);
            logger.info("Review {} is updated for user {} of Movie {} ", reviewId, user.getUsername(), movie.getOriginalTitle());
        } catch (Exception e) {
            logger.error("Review {} is not updated for user {} of Movie {} ", reviewId, user.getUsername(), movie.getOriginalTitle());
            throw new SpringShowException("Unable to update review ");
        }
    }

    public MovieFullDetailDto getMovieFullDetails(Movie movie) {
        var collectionDto = movieMapper.collectionEntityToDto(movie.getBelongsToCollection());
        var genreDto = movie.getGenres().stream()
                .map(movieMapper::genreEntityToDto)
                .toList();
        var productionCompanyDto = movie.getProductionCompanies().stream()
                .map(movieMapper::productionEntityToDto)
                .toList();
        var languageDto = movie.getSpokenLanguages().stream()
                .map(movieMapper::languageEntityToDto)
                .toList();
        var resultMovie = movieMapper.fullMovieEntityToDto(movie);
        resultMovie.setBelongsToCollection(collectionDto);
        resultMovie.setGenres(genreDto);
        resultMovie.setProductionCompanies(productionCompanyDto);
        resultMovie.setSpokenLanguages(languageDto);
        return resultMovie;
    }

    public String getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
