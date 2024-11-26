package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.FlightDTO;
import com.mgbooking.server.DTOS.FlightListDto;
import com.mgbooking.server.DTOS.FlightPaginateDTo;
import com.mgbooking.server.Entities.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FlightService {
public boolean CreateFlight(FlightDTO flightDTO);
public FlightListDto GetFlight(int id);
public Page<FlightPaginateDTo>FindAllByCountry(Pageable pageable, int id);
public boolean UpdateInformationFlight(FlightListDto flightListDto);
}
