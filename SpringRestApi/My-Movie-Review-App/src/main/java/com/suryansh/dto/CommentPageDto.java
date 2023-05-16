package com.suryansh.dto;

import java.util.List;

public record CommentPageDto(int page, List<CommentDto> result, int total_pages, Long total_results) {
}
