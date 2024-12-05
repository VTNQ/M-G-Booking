package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.*;


import java.math.BigDecimal;
import java.util.List;

public interface FlightService {
    public Object CreateFlight(String token, FlightDTO flightDTO);
    public FlightListDto GetFlights(String token, int id);
    public Object UpdateFlight(String token, FlightListDto flightDTO);
    public List<ResultFlightDTO>SearchFlights(int departureAirport, int arrivalAirport,
                                            String departureTime,
                                              String TypeFlight);
    public BigDecimal FindMinPrice(String departureTime);
    public List<ResultFlightDTO>SearchFlightsByArrivalTime(int departureAirport,int arrivalAirport,
                                                           String departureTime,String ArrivalTime,String TypeFlight);
    public List<FlightPaginateDTo>ShowAll(String token, int id);
}
