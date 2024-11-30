package com.mgbooking.server.Repositories;

import com.mgbooking.server.DTOS.FlightPaginateDTo;
import com.mgbooking.server.DTOS.ResultFlightDTO;
import com.mgbooking.server.Entities.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    @Query("SELECT f " +
            "FROM Flight f WHERE f.id = :id")
    public Flight findFlightWithDetails(@Param("id") Integer id);
    @Query("select new com.mgbooking.server.DTOS.FlightPaginateDTo(f.id,f.airline.name,f.departureAirport.name,f.arrivalAirport.name,f.departureTime,f.arrivalTime) from Flight f where f.airline.country.id = :id")
    public Page<FlightPaginateDTo>FindAllFlights(Pageable pageable, @Param("id") Integer id);
    @Query("SELECT MIN(G.price) FROM Flight f Join DetailFlight G on f.id=G.idFlight.id where  DATE(f.departureTime) = :departureTime")
    public BigDecimal FindPrice(@Param("departureTime") LocalDate departureTime);
    @Query("SELECT new com.mgbooking.server.DTOS.ResultFlightDTO(f.id,d.imageUrl,f.arrivalAirport.city.name,f.arrivalTime,f.departureTime," +
            "G.price,c.name,f.departureTime,f.arrivalTime,f.departureTime,f.arrivalTime,f.departureAirport.name) FROM Flight f " +
            "JOIN Airport a on a.id=f.departureAirport.id " +
            "JOIN Airport b on b.id=f.arrivalAirport.id " +
            "JOIN Airline c on c.id=f.airline.id "+
            "JOIN Picture d on c.id=d.airlineId "+
            "Join DetailFlight G on f.id=G.idFlight.id "+
            "join City e on e.id=b.city.id "+
            "WHERE f.departureAirport.id= :departureAirport " +
            "AND f.arrivalAirport.id = :arrivalAirport " +
            "AND DATE(f.departureTime) = :departureTime "+
             "And G.type = :TypeFlight")
    List<ResultFlightDTO> findFlightsByAirportsAndDepartureTime(
            @Param("departureAirport") int departureAirport,
            @Param("arrivalAirport") int arrivalAirport,
            @Param("departureTime") LocalDate departureTime,
            @Param("TypeFlight") String TypeFlight );


}
