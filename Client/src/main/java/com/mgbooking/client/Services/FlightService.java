package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.FlightDTO;
import com.mgbooking.client.DTO.FlightListDto;
import com.mgbooking.client.DTO.ResultFlightDTO;
import com.mgbooking.client.DTO.SearchFlightDTO;

import java.math.BigDecimal;
import java.util.List;

public interface FlightService {
    public Object CreateFlight(String token, FlightDTO flightDTO);
    public FlightListDto GetFlights(String token, int id);
    public Object UpdateFlight(String token, FlightListDto flightDTO);
    public List<ResultFlightDTO>SearchFlights(SearchFlightDTO searchFlightDTO);
    public BigDecimal FindMinPrice(String departureTime);
}
