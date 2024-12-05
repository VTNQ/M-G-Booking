package com.mgbooking.server.Repositories;

import com.mgbooking.server.DTOS.AirPortList;
import com.mgbooking.server.DTOS.SearchAiportDTO;
import com.mgbooking.server.Entities.Airport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Integer> {

    @Query("select t from Airport t where t.city.country.id = :id")
    List<Airport> findAllByCountryId(@Param("id") int id);
    @Query("select new com.mgbooking.server.DTOS.SearchAiportDTO(t.id,t.name,t.city,t.city.country.name) from  Airport t where t.name like %:SearchName% or  t.city.name like %:SearchName%")
    List<SearchAiportDTO>SearchAirPort(@Param("SearchName") String SearchName);

}

