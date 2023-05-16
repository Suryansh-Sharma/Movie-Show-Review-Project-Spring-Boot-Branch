package com.suryansh.Repository;

import com.suryansh.Entity.Movie;
import com.suryansh.Entity.ProductionCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductionCompanyRepository extends JpaRepository<ProductionCompany, Integer> {
    ProductionCompany findByName(String name);

    @Query("select m from Movie m join m.productionCompanies p where p.name = :name")
    Page<Movie> getMovieByProductionName(@Param(value = "name") String name, Pageable pageable);
}