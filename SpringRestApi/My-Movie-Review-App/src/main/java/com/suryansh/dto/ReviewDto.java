package com.suryansh.dto;

public record ReviewDto(Long id, int movieRating, String bestActor, String plotRating, String visualRatings,
                        String soundRating,
                        String recommend,
                        String otherComments) {
}
