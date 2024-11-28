package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.DetailFlight;

import java.util.List;

public interface DetailFlightService {
    public List<DetailFlight>findDetail(String token,int id);

}
