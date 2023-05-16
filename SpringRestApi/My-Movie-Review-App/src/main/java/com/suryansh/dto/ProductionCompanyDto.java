package com.suryansh.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductionCompanyDto {
    private int id;
    private String logoPath;
    private String name;
    private String originCountry;
}
