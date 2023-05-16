package com.suryansh.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "spoken_language")
public class SpokenLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String englishName;
    private String iso_639_1;
    private String name;
    @ManyToMany(mappedBy = "spokenLanguages", fetch = FetchType.LAZY)
    private List<Movie> movies;
}
