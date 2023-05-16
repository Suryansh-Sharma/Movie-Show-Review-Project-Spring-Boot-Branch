package com.suryansh.component;

import com.suryansh.Entity.*;
import com.suryansh.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieMapper {
    private final ModelMapper modelMapper;

    public MovieMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MovieFullDetailDto fullMovieEntityToDto(Movie movie) {
        return modelMapper.map(movie, MovieFullDetailDto.class);
    }

    public MovieDto movieEntityToDto(Movie movie) {
        return modelMapper.map(movie, MovieDto.class);
    }

    public CollectionDto collectionEntityToDto(Collection collection) {
        return modelMapper.map(collection, CollectionDto.class);
    }

    public GenreDto genreEntityToDto(Genre genre) {
        return modelMapper.map(genre, GenreDto.class);
    }

    public ProductionCompanyDto productionEntityToDto(ProductionCompany productionCompany) {
        return modelMapper.map(productionCompany, ProductionCompanyDto.class);
    }

    public SpokenLanguageDto languageEntityToDto(SpokenLanguage language) {
        return modelMapper.map(language, SpokenLanguageDto.class);
    }

    public CommentDto commentEntityToDto(Comments comments) {
        return modelMapper.map(comments, CommentDto.class);
    }
}
