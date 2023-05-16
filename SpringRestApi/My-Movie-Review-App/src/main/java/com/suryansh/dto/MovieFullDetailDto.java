package com.suryansh.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieFullDetailDto {
    private int id;
    private boolean adult;
    private String backdropPath;
    private CollectionDto belongsToCollection;
    private int budget;
    private List<GenreDto> genres;
    private String homepage;
    private String imdbId;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private double popularity;
    private String posterPath;
    private List<ProductionCompanyDto> productionCompanies;
    private String releaseDate;
    private long revenue;
    private int runtime;
    private List<SpokenLanguageDto> spokenLanguages;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private double voteAverage;
    private int voteCount;
}
