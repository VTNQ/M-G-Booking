package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.FlightDTO;
import com.mgbooking.client.DTO.ListFlightDto;
import com.mgbooking.client.DTO.UpdateFlightDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface AirlineService {
    public Object CreateFlight(FlightDTO flightDTO, String token);
    public List<ListFlightDto>findAll(String token);
    public UpdateFlightDTO FindFlight(String token,int id);
    public boolean UpdateFlight(UpdateFlightDTO updateFlightDTO, String token, MultipartFile file);
}
