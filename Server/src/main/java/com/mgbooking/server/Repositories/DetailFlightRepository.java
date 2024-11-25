package com.mgbooking.server.Repositories;

import com.mgbooking.server.DTOS.DetailFlightDTO;
import com.mgbooking.server.Entities.DetailFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailFlightRepository extends JpaRepository<DetailFlight,Integer> {
    @Query("select d FROM DetailFlight d WHERE d.idFlight.id = :id")
    public List<DetailFlight> findDetailFlightsByFlightId(@Param("id") Integer id);
}
