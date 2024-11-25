package com.mgbooking.server.Repositories;

import com.mgbooking.server.DTOS.AirPortList;
import com.mgbooking.server.Entities.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
    @Query("select new com.mgbooking.server.DTOS.AirPortList(t.id,t.name,t.city) from Airport t where t.city.country.id = :id")
    Page<Airport> findAll(@Param("id") int id, Pageable pageable);
    @Query("select t from Airport t where t.city.country.id = :id")
    List<Airport> findAllByCountryId(@Param("id") int id);


}

