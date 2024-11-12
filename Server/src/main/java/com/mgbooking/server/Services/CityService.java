package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.CityDTO;
import com.mgbooking.server.Entities.City;

import java.util.List;

public interface CityService {
    public List<City>GetAllCities(int id);
    public boolean AddCity(CityDTO city);
}
