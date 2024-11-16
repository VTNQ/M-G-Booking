package com.mgbooking.server.Repositories;

import com.mgbooking.server.Entities.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    @Query("select c from City c where c.country.id = :countryId")
    List<City> findCitiesByCountryId(@Param("countryId") int countryId);
    @Query("select c from City c where c.country.id = :countryId")
    Page<City>findCitiesByCountryId(@Param("countryId") int countryId, Pageable pageable);

}
