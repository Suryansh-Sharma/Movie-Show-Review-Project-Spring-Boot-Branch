package com.suryansh.dto;

import com.suryansh.Entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CollectionDto {
    private String name;
    private String posterPath;
    private String backdropPath;
    @OneToOne
    private Movie movie;
}
