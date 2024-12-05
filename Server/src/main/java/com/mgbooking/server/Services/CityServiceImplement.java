package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.CityDTO;
import com.mgbooking.server.Entities.City;
import com.mgbooking.server.Entities.Country;
import com.mgbooking.server.Repositories.CityRepository;
import com.mgbooking.server.Repositories.CountryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CityServiceImplement implements CityService{
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<City> GetAllCities(int id) {
        return modelMapper.map(cityRepository.findCitiesByCountryId(id),new TypeToken<List<City>>(){}.getType());
    }

    @Override
    public boolean AddCity(CityDTO city) {
        try {
            Country country = countryRepository.findById(city.getCountry_id())
                    .orElseThrow(() -> new RuntimeException("Country not found"));
            City cityDto =modelMapper.map(city, City.class);
            cityDto.setCountry(country);
            City insertCity=cityRepository.save(cityDto);
            return insertCity!=null && insertCity.getId()>0;
        }catch (Exception e){
            return false;
        }
    }



    @Override
    public CityDTO FindCity(int id) {
        return modelMapper.map(cityRepository.findById(id),new TypeToken<CityDTO>(){}.getType());
    }

    @Override
    public boolean DeleteCity(int id) {
        try {
            cityRepository.deleteById(id);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
}
