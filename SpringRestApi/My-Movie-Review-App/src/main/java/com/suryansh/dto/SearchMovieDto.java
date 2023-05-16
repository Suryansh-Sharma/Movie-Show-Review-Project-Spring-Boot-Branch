package com.suryansh.dto;

import java.util.List;

public record SearchMovieDto(String searched_for, List<result> result, int total_record) {
    public record result(int id, String name, List<String> genres) {
    }
}
