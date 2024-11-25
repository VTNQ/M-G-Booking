package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.AirPortDTO;
import com.mgbooking.server.DTOS.AirPortList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AirPortService {
    public boolean AddAirPort(AirPortDTO airportDTO);
    public Page<AirPortList>GetAll(int id, Pageable pageable);
    public AirPortDTO FindById(int id);
    public List<AirPortDTO>FindAirPortByCountry(int id);
}
