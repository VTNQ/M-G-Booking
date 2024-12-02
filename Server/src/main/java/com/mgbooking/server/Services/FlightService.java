package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.*;
import com.mgbooking.server.Entities.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface FlightService {
public boolean CreateFlight(FlightDTO flightDTO);
public FlightListDto GetFlight(int id);
public Page<FlightPaginateDTo>FindAllByCountry(Pageable pageable, int id);
public boolean UpdateInformationFlight(FlightListDto flightListDto);
public List<ResultFlightDTO>SearchFlight(int departureAirport, int arrivalAirport, LocalDate departureTime,String TypeFlight);
public BigDecimal FindPrice(LocalDate departureTime);
public List<ResultFlightDTO>SearchFlightAllDto(int departureAirport, int arrivalAirport, LocalDate departureTime,LocalDate ArrivalTime,String TypeFlight);
}
