package com.suryansh.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CastDto {
    private String name;
    private String profilePath;
    private String character;
}
