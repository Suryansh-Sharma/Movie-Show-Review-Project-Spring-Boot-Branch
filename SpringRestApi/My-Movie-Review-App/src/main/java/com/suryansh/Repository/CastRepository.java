package com.suryansh.Repository;

import com.suryansh.Entity.Cast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CastRepository extends JpaRepository<Cast, Integer> {
}