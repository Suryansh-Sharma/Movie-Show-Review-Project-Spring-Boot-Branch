package com.suryansh.dto;

import java.util.List;

public record MoviePagingDto(int page, List<MovieDto> result, int total_pages, Long total_results) {
}
