package com.suryansh.dto;

import java.util.List;

public record MovieCredits(int movieId, List<CastDto> cast) {
}
