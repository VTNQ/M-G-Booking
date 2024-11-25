package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.AirLineDTO;
import com.mgbooking.server.DTOS.ListFlightDto;
import com.mgbooking.server.DTOS.UpdateFlightDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AirlineService {
    public boolean AddFlight(AirLineDTO flightDTO);
    public Page<ListFlightDto> findAll(Pageable pageable);
    public UpdateFlightDTO FindFlight(int id);
    public boolean UpdateFlight(UpdateFlightDTO flightDTO,MultipartFile file);
    public List<ListFlightDto>ShowAirlineDto(int id);
}
