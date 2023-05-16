package com.suryansh.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SpokenLanguageDto {
    private int id;
    private String englishName;
    private String iso_639_1;
    private String name;
}
