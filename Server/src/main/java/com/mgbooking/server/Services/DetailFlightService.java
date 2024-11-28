package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.DetailFlightDTO;
import com.mgbooking.server.Entities.DetailFlight;

import java.util.List;

public interface DetailFlightService {
    public List<DetailFlightDTO>FindAll(int id);
    public boolean UpdateDetailFlight(List<DetailFlightDTO> detailFlight);
}
