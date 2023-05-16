package com.suryansh.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String theMovieDbId;
    private String name;
    private String posterPath;
    private String backdropPath;
    @OneToOne
    private Movie movie;
}
