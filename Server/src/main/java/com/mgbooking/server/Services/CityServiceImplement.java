package com.mgbooking.server.Services;

import com.mgbooking.server.Entities.City;
import com.mgbooking.server.Repositories.CityRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityServiceImplement implements CityService{
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<City> GetAllCities(int id) {
        return modelMapper.map(cityRepository.findCitiesByCountryId(id),new TypeToken<List<City>>(){}.getType());
    }
}
