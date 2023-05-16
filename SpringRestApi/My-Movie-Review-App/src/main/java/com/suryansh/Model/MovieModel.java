package com.suryansh.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovieModel {

    @NotNull(message = "Adult field cannot be null")
    private Boolean adult;

    @NotBlank(message = "Backdrop path cannot be blank")
    private String backdrop_path;
    @NotNull(message = "Belongs to collection should not be null")
    private BelongsToCollection belongs_to_collection;

    @Min(value = 1, message = "Budget must be greater than 0")
    private Integer budget;

    @NotEmpty(message = "Genres list cannot be empty")
    private List<Genre> genres;

    private String homepage;

    @Min(value = 1, message = "Id must be greater than 0")
    private Integer id;

    @NotBlank(message = "IMDB Id cannot be blank")
    private String imdb_id;

    @NotBlank(message = "Original Language cannot be blank")
    private String original_language;

    @NotBlank(message = "Original Title cannot be blank")
    private String original_title;

    @NotBlank(message = "Overview cannot be blank")
    private String overview;

    @DecimalMin(value = "0.1", message = "Popularity must be greater than 0")
    private Double popularity;

    @NotBlank(message = "Poster path cannot be blank")
    private String poster_path;

    @NotEmpty(message = "Production companies list cannot be empty")
    private List<ProductionCompany> production_companies;

    @NotEmpty(message = "Production countries list cannot be empty")
    private List<ProductionCountry> production_countries;

    @NotBlank(message = "Release date cannot be blank")
    private String release_date;

    @Min(value = 1, message = "Revenue must be greater than 0")
    private Integer revenue;

    @Min(value = 1, message = "Runtime must be greater than 0")
    private Integer runtime;

    @NotEmpty(message = "Spoken languages list cannot be empty")
    private List<SpokenLanguage> spoken_languages;

    @NotBlank(message = "Status cannot be blank")
    private String status;

    private String tagline;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotNull(message = "Video field cannot be null")
    private Boolean video;

    @DecimalMin(value = "0.1", message = "Vote average must be greater than 0")
    @DecimalMax(value = "10.0", message = "Vote average must be less than or equal to 10")
    private Double vote_average;

    @Min(value = 1, message = "Vote count must be greater than 0")
    private Integer vote_count;

    @Data
    @Builder

    public static class BelongsToCollection {
        private String theMovieDbId;
        @NotNull(message = "BelongsToCollection.name field cannot be null")
        private String name;
        @NotNull(message = "BelongsToCollection.poster_path field cannot be null")
        private String poster_path;
        @NotNull(message = "BelongsToCollection.backdrop_path field cannot be null")
        private String backdrop_path;
    }

    @Data
    @Builder

    public static class Genre {
        private int id;
        private String name;
    }

    @Data
    @Builder

    public static class ProductionCompany {
        private int id;
        private String logo_path;
        private String name;
        private String origin_country;
    }

    @Data
    @Builder

    public static class ProductionCountry {
        private String iso_3166_1;
        private String name;
    }

    @Data
    @Builder

    public static class SpokenLanguage {
        private String english_name;
        private String iso_639_1;
        private String name;
    }
}
