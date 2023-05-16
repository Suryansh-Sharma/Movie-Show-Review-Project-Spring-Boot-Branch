package com.suryansh.Repository;

import com.suryansh.Entity.SpokenLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpokenLanguageRepository extends JpaRepository<SpokenLanguage, Integer> {
    SpokenLanguage findByEnglishName(String englishName);
}