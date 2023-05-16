package com.suryansh.Service.impl;

import com.suryansh.Entity.*;
import com.suryansh.Exception.SpringShowException;
import com.suryansh.Model.CastModel;
import com.suryansh.Model.CommentModel;
import com.suryansh.Model.MovieModel;
import com.suryansh.Repository.*;
import com.suryansh.component.MovieMapper;
import com.suryansh.dto.CommentDto;
import com.suryansh.dto.CommentPageDto;
import com.suryansh.dto.MovieDto;
import com.suryansh.dto.MoviePagingDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImplTest.class);
    static Movie movie;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private GenreRepository genreRepository;
    @Mock
    private ProductionCompanyRepository productionRepository;
    @Mock
    private SpokenLanguageRepository spokenLanguageRepository;
    @Mock
    private MovieMapper movieMapper;
    @Mock
    private PersonRepository personRepository;
    @Mock
    private CommentsRepository commentsRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private MovieServiceImpl movieService;

    @BeforeAll
    static void setUp() {
        logger.info("Testing starts !!");
        movie = Movie.builder().title("Shazam")
                .genres(List.of(Genre.builder().name("Action").build()))
                .productionCompanies(List.of(ProductionCompany.builder().name("Production Company").build()))
                .spokenLanguages(List.of(SpokenLanguage.builder().englishName("English").build()))
                .belongsToCollection(Collection.builder()
                        .name("Collection Name")
                        .backdropPath("/path/to/collection/backdrop")
                        .posterPath("/path/to/collection/poster")
                        .build())
                .movieCast(new ArrayList<>())
                .build();
    }

    @AfterAll
    static void tearDown() {
        logger.info("YaY !! , Testing completed");
    }

    @Test
    void testSaveMovie_Success() {
        MovieModel movieModel = MovieModel.builder()
                .adult(false)
                .backdrop_path("/path/to/backdrop")
                .belongs_to_collection(MovieModel.BelongsToCollection.builder()
                        .name("Collection Name")
                        .backdrop_path("/path/to/collection/backdrop")
                        .poster_path("/path/to/collection/poster")
                        .build())
                .budget(10000000)
                .genres(List.of(MovieModel.Genre.builder().name("Action").build()))
                .homepage("")
                .imdb_id("tt1234567")
                .original_language("en")
                .original_title("Original Title")
                .overview("Movie overview")
                .popularity(8.5)
                .poster_path("/path/to/poster")
                .production_companies(List.of(MovieModel.ProductionCompany.builder()
                        .name("Production Company")
                        .logo_path("/path/to/logo")
                        .origin_country("US")
                        .build()))
                .release_date(Instant.now().toString())
                .revenue(20000000)
                .runtime(120)
                .spoken_languages(List.of(MovieModel.SpokenLanguage.builder()
                        .english_name("English")
                        .iso_639_1("en")
                        .name("English")
                        .build()))
                .status("Released")
                .tagline("Movie tagline")
                .title("Movie Title")
                .video(false)
                .vote_average(7.5)
                .vote_count(1000)
                .build();
        when(movieRepository.findByTitle(anyString())).thenReturn(Optional.empty());
        when(genreRepository.saveAll(any())).thenReturn(List.of());
        when(productionRepository.saveAll(any())).thenReturn(List.of());
        when(spokenLanguageRepository.saveAll(any())).thenReturn(List.of());

        String result = movieService.saveMovie(movieModel);
        assertThat("Movie is successfully saved !!").isEqualTo(result);
        verify(productionRepository, times(1)).findByName(eq("Production Company"));
    }

    @Test
    public void testSaveMovie_MovieAlreadyPresent() {
        MovieModel movieModel = MovieModel.builder()
                .title("Shazam")
                .genres(null)
                .build();
        when(movieRepository.findByTitle("Shazam")).thenReturn(Optional.of(new Movie()));
        assertThatThrownBy(() -> movieService.saveMovie(movieModel))
                .isInstanceOf(SpringShowException.class)
                .hasMessageContaining("Movie is already present !!");
    }

    @Test
    public void testSaveMovie_GenreNotPresent() {
        // Create a movie model with an empty list of genres
        MovieModel movieModel = MovieModel.builder()
                .title("Shazam")
                .genres(null)
                .build();
        // Act and Assert
        assertThatThrownBy(() -> movieService.saveMovie(movieModel))
                .isInstanceOf(SpringShowException.class)
                .hasMessageContaining("Genres is empty for movie");
    }

    @Test
    public void testSaveMovie_CompaniesNotPresent() {
        // Create a movie model with an empty list of production companies
        MovieModel movieModel = MovieModel.builder()
                .title("Shazam")
                .genres(List.of(MovieModel.Genre.builder().name("Action").build()))
                .production_companies(null)
                .build();
        // Act and Assert
        assertThatThrownBy(() -> movieService.saveMovie(movieModel))
                .isInstanceOf(SpringShowException.class)
                .hasMessageContaining("Production is empty for movie");
    }

    @Test
    public void testSaveMovie_SpokenLangNotPresent() {
        // Create a movie model with an empty list of production companies
        MovieModel movieModel = MovieModel.builder()
                .title("Shazam")
                .genres(List.of(MovieModel.Genre.builder().name("Action").build()))
                .production_companies(List.of(MovieModel.ProductionCompany.builder().name("WarnerBros").build()))
                .spoken_languages(null)
                .build();
        // Act and Assert
        assertThatThrownBy(() -> movieService.saveMovie(movieModel))
                .isInstanceOf(SpringShowException.class)
                .hasMessageContaining("Spoken Language is empty for movie");
    }

    @Test
    void testGetMovieFullDetailByName_NotPresent() {
        // Create a movie model with an empty list of production companies
        String name = "Not_Present";
        // Act and Assert
        assertThatThrownBy(() -> movieService.getMovieFullDetailByName(name))
                .isInstanceOf(SpringShowException.class)
                .hasMessageContaining("Unable to find movie of name " + name);
    }

    @Test
    void testGetMovieFullDetailById_NotPresent() {
        // Create a movie model with an empty list of production companies
        int id = 1001;
        // Act and Assert
        assertThatThrownBy(() -> movieService.getMovieFullDetailById(id))
                .isInstanceOf(SpringShowException.class)
                .hasMessageContaining("Unable to find movie of id " + id);
    }

    @Test
    void getAllShows() {
        // arrange
        List<Movie> movieEntities = List.of(movie);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Movie> movieEntityPage = new PageImpl<>(movieEntities, pageable, 3);
        // mock
        when(movieRepository.findAll(pageable)).thenReturn(movieEntityPage);
        when(movieMapper.movieEntityToDto(any(Movie.class))).thenAnswer(
                (InvocationOnMock invocation) -> {
                    Movie movieEntity = invocation.getArgument(0);
                    return MovieDto.builder().title(movieEntity.getTitle()).build();
                });
        // act
        MoviePagingDto result = movieService.getAllShows(pageable);
        // assert
        assertThat(result.page()).isEqualTo(1);
        assertThat(result.result().get(0).getTitle()).isEqualTo(movie.getTitle());
    }

    @Test
    void getByGenre() {
        // arrange
        String genre = "Action";
        List<Movie> movieEntities = List.of(movie);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Movie> movieEntityPage = new PageImpl<>(movieEntities, pageable, 3);

        when(genreRepository.findMovieByGenre(genre, pageable)).thenReturn(movieEntityPage);
        when(movieMapper.movieEntityToDto(any(Movie.class))).thenAnswer(
                (InvocationOnMock invocation) -> {
                    Movie movieEntity = invocation.getArgument(0);
                    return MovieDto.builder().title(movieEntity.getTitle()).build();
                });
        // act
        MoviePagingDto result = movieService.getByGenre(genre, pageable);
        // assert
        assertThat(result.page()).isEqualTo(1);
        assertThat(result.result().get(0).getTitle()).isEqualTo(movie.getTitle());
    }

    @Test
    void testSaveCast_MovieNotPresent() {
        // Arrange
        CastModel castModel = new CastModel("Tom Cruise", "profile_path", "Ethan");
        var castModels = List.of(castModel);
        int movieId = -1;
        // Assert
        assertThatThrownBy(() -> movieService.saveCast(castModels, movieId))
                .isInstanceOf(SpringShowException.class)
                .hasMessageContaining("Unable to find movie of id " + movieId);
    }

    @Test
    void testSaveCast_Success_PersonPresent() {
        // Arrange
        CastModel cast1 = new CastModel("Zachary Levi", "profile_path", "Shazam");
        CastModel cast2 = new CastModel("Asher Angel", "profile_path", "Billy Batson");
        CastModel cast3 = new CastModel("Jack Dylan Grazer", "profile_path", "Freddy Freeman");

        var castModels = List.of(cast1, cast2, cast3);

        var castEntities = new ArrayList<Cast>();

        // Mock
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
        for (var val : castModels) {
            Person person = Person.builder().name(val.getName()).build();
            lenient().when(personRepository.findByName(any())).thenReturn(person);
            var cast = Cast.builder().name(val.getName()).build();
            castEntities.add(cast);
        }
        movie.getMovieCast().addAll(castEntities);
        when(movieRepository.save(movie)).thenReturn(movie);
        // Act
        movieService.saveCast(castModels,1);
        // Assert
        assertThat(movie.getMovieCast().get(0).getName()).isEqualTo(cast1.getName());
    }

    @Test
    void testSaveCast_Success_PersonNotPresent() {
        // Arrange
        CastModel cast1 = new CastModel("Zachary Levi", "profile_path", "Shazam");
        CastModel cast2 = new CastModel("Asher Angel", "profile_path", "Billy Batson");
        CastModel cast3 = new CastModel("Jack Dylan Grazer", "profile_path", "Freddy Freeman");

        var castModels = List.of(cast1, cast2, cast3);

        var castEntities = new ArrayList<Cast>();

        // Mock
        when(movieRepository.findById(any())).thenReturn(Optional.of(movie));
        for (var val : castModels) {
            when(personRepository.findByName(any())).thenReturn(null);

            // Create new person and firstly save in PersonRepository.
            var person = Person.builder()
                    .name(val.getName())
                    .build();
            lenient().when(personRepository.save(person)).thenReturn(null);

            var cast = Cast.builder().name(person.getName()).build();
            castEntities.add(cast);
        }
        movie.getMovieCast().addAll(castEntities);
        // Act
        movieService.saveCast(castModels,1);
        // Assert
        assertThat(movie.getMovieCast().get(0).getName()).isEqualTo(cast1.getName());
    }

    @Test
    void testGetCreditsOfMovie_Success() {
        // Arrange
        var cast1 = Cast.builder().name("Zachary Levi").character("Shazam").build();
        var cast2 = Cast.builder().name("Asher Ange").character("Billy Batson").build();
        var castList = List.of(cast1, cast2);
        movie.getMovieCast().addAll(castList);
        // Mock
        when(movieRepository.findById(any())).thenReturn(Optional.of(movie));
        // Act
        var result = movieService.getCreditsOfMovie(1);
        // Assert
        assertThat(result.movieId()).isEqualTo(1);
        assertThat(result.cast().size()).isEqualTo(2);
        assertThat(result.cast().get(0).getName()).isEqualTo("Zachary Levi");
    }

    @Test
    void testGetCreditsOfMovie_MovieNotPresent() {
        // Arrange
        int movieId = -1;
        // Assert
        assertThatThrownBy(() -> movieService.getCreditsOfMovie(movieId))
                .isInstanceOf(SpringShowException.class)
                .hasMessageContaining("Unable to find movie of id " + movieId);
    }

    @Test
    void testGetMovieByPerson_Success() {
        // Arrange
        Person person = Person.builder().name("Zachary Levi").build();
        List<Movie> movieEntities = List.of(movie);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Movie> movieEntityPage = new PageImpl<>(movieEntities, pageable, 3);
        // Mock
        when(personRepository.findByName(person.getName())).thenReturn(person);
        when(movieRepository.findByMovieCastPerson(person, pageable)).thenReturn(movieEntityPage);
        when(movieMapper.movieEntityToDto(any(Movie.class))).thenAnswer(
                (InvocationOnMock invocation) -> {
                    Movie movieEntity = invocation.getArgument(0);
                    return MovieDto.builder().title(movieEntity.getTitle()).build();
                });
        // Act
        MoviePagingDto moviePagingDto = movieService.getMovieByPerson(person.getName(), pageable);
        // Assert
        assertThat(moviePagingDto.result().get(0).getTitle())
                .isEqualTo(movie.getTitle());
        assertThat(moviePagingDto.page())
                .isEqualTo(pageable.getPageNumber() + 1);
    }

    @Test
    void testGetMovieByPerson_PersonNotPresent() {
        // Arrange
        var personName = "Not_Present";
        Pageable pageable = PageRequest.of(0, 6);
        // Assert
        assertThatThrownBy(() -> movieService.getMovieByPerson(personName, pageable))
                .isInstanceOf(SpringShowException.class)
                .hasMessageContaining("Unable to find person " + personName);
    }

    @Test
    void searchMovie() {
    }

    @Test
    void testGetShowComments() {
        // Arrange
        var username = "Suryansh";
        Pageable pageable = PageRequest.of(0, 6);
        Comments comments1 = Comments.builder().userName(username).dateOfComment(Instant.now()).note("Amazing Movie").build();
        var commentsEntities = List.of(comments1);
        var commentPage = new PageImpl<>(commentsEntities, pageable, 1);
        // Mock
        when(commentsRepository.getAllCommentsByMovieId(1, pageable)).thenReturn(commentPage);
        when(movieMapper.commentEntityToDto(any(Comments.class))).thenAnswer(
                (InvocationOnMock invocation) -> new CommentDto(1L, username, "profile_pic", comments1.getNote(), comments1.getDateOfComment()));
        // Act
        CommentPageDto commentPageDto = movieService.getShowComments(1, pageable);
        // Assert
        assertThat(commentPageDto.page()).isEqualTo(pageable.getPageNumber() + 1);
        assertThat(commentPageDto.result().get(0).getUserName()).isEqualTo(username);
        assertThat(commentPageDto.result().get(0).getNote()).isEqualTo(comments1.getNote());
    }

    @Test
    void testAddNewComment_Success() {
        // Arrange
        CommentModel commentModel = new CommentModel(1, "Amazing Movie");
        var user = User.builder().username("Suryansh Sharma").role(Role.USER).build();
        Comments comments = Comments.builder().userName(user.getUsername()).dateOfComment(Instant.now()).note("Amazing Movie").build();

        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn(user.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Mock
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        lenient().when(commentsRepository.save(comments)).thenReturn(null);

        // Act
        movieService.addNewComment(commentModel);
        // Assert
        verify(commentsRepository, times(1)).save(any(Comments.class));
    }

    @Test
    void testAddNewComment_MovieNotPresent() {
        // Arrange
        CommentModel commentModel = new CommentModel(1, "Amazing Movie");
        // Act and Assert
        assertThatThrownBy(() -> movieService.addNewComment(commentModel)).isInstanceOf(SpringShowException.class);
    }

    @Test
    void testAddNewComment_UserNotPresent() {
        // Arrange
        CommentModel commentModel = new CommentModel(1, "Amazing Movie");
        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("username");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Mock
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
        when(userRepository.findByUsername("username")).thenReturn(Optional.empty());
        // Act and Assert
        assertThatThrownBy(() -> movieService.addNewComment(commentModel)).isInstanceOf(SpringShowException.class);
    }

    @Test
    void testGetMoviesByProduction_Success() {
        // Arrange
        String productionName = "Production";
        Pageable pageable = PageRequest.of(0, 10);
        List<Movie> movies = List.of(movie);
        Page<Movie> page = new PageImpl<>(movies, pageable, 2);
        // Mock
        when(productionRepository.getMovieByProductionName(productionName, pageable)).thenReturn(page);
        when(movieMapper.movieEntityToDto(any(Movie.class))).thenReturn(new MovieDto());
        // Act
        MoviePagingDto result = movieService.getMoviesByProduction(productionName, pageable);
        // Assert
        assertThat(result.page()).isEqualTo(pageable.getPageNumber());
        assertThat(result.total_pages()).isEqualTo(page.getTotalPages());
        assertThat(result.total_results()).isEqualTo(page.getTotalElements());
        assertThat(result.result().size()).isEqualTo(movies.size());
    }

}