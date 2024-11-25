package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.AirPortDTO;
import com.mgbooking.server.DTOS.AirPortList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AirPortService {
    public boolean AddAirPort(AirPortDTO airportDTO);
    public Page<AirPortList>GetAll(int id, Pageable pageable);
    public AirPortDTO FindById(int id);
}
