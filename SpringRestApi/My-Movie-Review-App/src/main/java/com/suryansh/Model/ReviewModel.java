package com.suryansh.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewModel {
    @Min(value = 1, message = "Minimum rating should be 1")
    @Max(value = 10, message = "Maximum rating should be 10")
    @NotNull(message = "Movie Rating can't be null")
    private int movieRating;

    @NotBlank(message = "Movie Best Actor can't be null")
    private String bestActor;

    @NotBlank(message = "Plot Rating can't be null")
    private String plotRating;

    @NotBlank(message = "Visual Rating can't be null")
    private String visualRatings;

    @NotBlank(message = "Sound Rating can't be null")
    private String soundRating;

    @NotBlank(message = "Recommendation Rating can't be null")
    private String recommend;

    private String otherComments;
}
