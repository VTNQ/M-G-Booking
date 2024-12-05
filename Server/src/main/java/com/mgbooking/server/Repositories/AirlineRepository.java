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

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AirlineRepository extends JpaRepository<Airline,Integer> {

    @Query("select new com.mgbooking.server.DTOS.UpdateFlightDTO(a.id,a.name,a.country.id,i.imageUrl) "+
           "From Airline a "+
            "join Picture i on a.id=i.airlineId "+
            "where a.id = :id")
    UpdateFlightDTO FindById(@Param("id") Integer id);
    @Query("SELECT new com.mgbooking.server.DTOS.ListFlightDto(a.id, a.name,  i.imageUrl, a.country.name) " +
            "FROM Airline a " +
            "JOIN Picture i ON a.country.id = :id")
    List<ListFlightDto> ShowAirlineDto(@Param("id") int id);
    @Query("select new com.mgbooking.server.DTOS.ListFlightDto(a.id,a.name,i.imageUrl,a.country.name) "+
            "from Airline a "
            + "join Picture i On a.id =i.airlineId")
    List<ListFlightDto> ShowAll();
    @Query("select a from Airline a Join Flight b on a.id=b.airline.id join DetailFlight c on c.idFlight.id=b.id " +
            " where b.departureAirport.id= :departureAirport"+
            " and b.arrivalAirport.id = :arrivalAirport"+
            " and Date(b.departureTime) = :departureTime"+
            " and c.type = :TypeFlight")
    List<Airline>SearchAirline( @Param("departureAirport") int departureAirport,
                                @Param("arrivalAirport") int arrivalAirport,
                                @Param("departureTime") LocalDate departureTime,
                                @Param("TypeFlight") String TypeFlight);
}
