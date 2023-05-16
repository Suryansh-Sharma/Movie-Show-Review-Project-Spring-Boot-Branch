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
@Table(name = "production_company")
public class ProductionCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String logoPath;
    private String name;
    private String originCountry;
    @ManyToMany(mappedBy = "productionCompanies", fetch = FetchType.LAZY)
    private List<Movie> movies;
}
