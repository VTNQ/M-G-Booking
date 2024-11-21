package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.FlightDTO;
import com.mgbooking.server.DTOS.ListFlightDto;
import com.mgbooking.server.DTOS.UpdateFlightDTO;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FlightService {
    public boolean AddFlight(FlightDTO flightDTO);
    public Page<ListFlightDto> findAll(Pageable pageable);
    public UpdateFlightDTO FindFlight(int id);
    public boolean UpdateFlight(UpdateFlightDTO flightDTO,MultipartFile file);
}
