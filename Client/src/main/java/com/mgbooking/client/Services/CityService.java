package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.CityDTO;

public interface CityService {
    public boolean CreateCity(String token, CityDTO cityDTO);
}
