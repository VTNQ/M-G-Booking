package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.FlightDTO;

public interface FlightService {
    public Object CreateFlight(String token, FlightDTO flightDTO);
}
