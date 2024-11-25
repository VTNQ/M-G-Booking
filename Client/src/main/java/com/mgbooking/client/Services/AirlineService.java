package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.AirlineDTO;
import com.mgbooking.client.DTO.ListFlightDto;
import com.mgbooking.client.DTO.UpdateFlightDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AirlineService {
    public Object CreateFlight(AirlineDTO flightDTO, String token);
    public List<ListFlightDto>findAll(String token);
    public UpdateFlightDTO FindFlight(String token,int id);
    public boolean UpdateFlight(UpdateFlightDTO updateFlightDTO, String token, MultipartFile file);
    public List<ListFlightDto>FindAirlineByCountry(String token,int id);
}
