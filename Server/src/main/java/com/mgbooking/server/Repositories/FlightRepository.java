package com.mgbooking.server.Repositories;

import com.mgbooking.server.DTOS.FlightDTO;
import com.mgbooking.server.DTOS.FlightPaginateDTo;
import com.mgbooking.server.Entities.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    @Query("SELECT f " +
            "FROM Flight f WHERE f.id = :id")
    public Flight findFlightWithDetails(@Param("id") Integer id);
    @Query("select new com.mgbooking.server.DTOS.FlightPaginateDTo(f.id,f.airline.name,f.departureAirport.name,f.arrivalAirport.name,f.departureTime,f.arrivalTime) from Flight f where f.airline.country.id = :id")
    public Page<FlightPaginateDTo>FindAllFlights(Pageable pageable, @Param("id") Integer id);
}
