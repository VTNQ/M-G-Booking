package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.CityDTO;
import com.mgbooking.server.Entities.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService {
    public List<City>GetAllCities(int id);
    public boolean AddCity(CityDTO city);
    public Page<City>findCity(int id,Pageable pageable);
    public CityDTO FindCity(int id);
    public boolean DeleteCity(int id);
}
