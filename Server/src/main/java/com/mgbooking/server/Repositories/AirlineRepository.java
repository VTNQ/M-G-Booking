package com.mgbooking.server.Repositories;

import com.mgbooking.server.DTOS.ListFlightDto;
import com.mgbooking.server.DTOS.UpdateFlightDTO;
import com.mgbooking.server.Entities.Airline;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineRepository extends JpaRepository<Airline,Integer> {
    @Query("SELECT new com.mgbooking.server.DTOS.ListFlightDto(a.id, a.name,  i.imageUrl, a.country.name) " +
            "FROM Airline a " +
            "JOIN Picture i ON i.airlineId = a.id")
    Page<ListFlightDto> AllAirlineDto(Pageable pageable);
    @Query("select new com.mgbooking.server.DTOS.UpdateFlightDTO(a.id,a.name,a.country.id,i.imageUrl) "+
           "From Airline a "+
            "join Picture i on a.id=i.airlineId "+
            "where a.id = :id")
    UpdateFlightDTO FindById(@Param("id") Integer id);
    @Query("SELECT new com.mgbooking.server.DTOS.ListFlightDto(a.id, a.name,  i.imageUrl, a.country.name) " +
            "FROM Airline a " +
            "JOIN Picture i ON a.country.id = :id")
    List<ListFlightDto> ShowAirlineDto(@Param("id") int id);
}