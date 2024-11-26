package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.FlightDTO;
import com.mgbooking.client.DTO.FlightListDto;

public interface FlightService {
    public Object CreateFlight(String token, FlightDTO flightDTO);
    public FlightListDto GetFlights(String token, int id);
    public Object UpdateFlight(String token, FlightListDto flightDTO);
}
