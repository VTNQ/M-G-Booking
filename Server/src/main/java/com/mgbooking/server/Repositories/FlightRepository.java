package com.mgbooking.server.Repositories;

import com.mgbooking.server.DTOS.FlightDTO;
import com.mgbooking.server.Entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    @Query("SELECT f " +
            "FROM Flight f WHERE f.id = :id")
    public Flight findFlightWithDetails(@Param("id") Integer id);
}
