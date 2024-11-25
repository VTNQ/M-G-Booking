package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.FlightDTO;
import com.mgbooking.server.DTOS.FlightListDto;

public interface FlightService {
public boolean CreateFlight(FlightDTO flightDTO);
public FlightListDto GetFlight(int id);
}
