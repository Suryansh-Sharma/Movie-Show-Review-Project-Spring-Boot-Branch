package com.suryansh.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int movieRating;
    private String bestActor;
    private String plotRating;
    private String visualRatings;
    private String soundRating;
    private String recommend;
    private String otherComments;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private User user;
}
