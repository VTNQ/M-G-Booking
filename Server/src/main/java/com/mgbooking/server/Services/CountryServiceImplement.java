package com.mgbooking.server.Services;

import com.mgbooking.server.Entities.Country;
import com.mgbooking.server.Repositories.CountryRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CountryServiceImplement implements CountryService {
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<Country> findAll() {
        return modelMapper.map(countryRepository.findAll(),new TypeToken<List<Country>>(){}.getType());
    }

    @Override
    public boolean CreateCountry(Country country) {
        try {
           Country saveCountry= countryRepository.save(country);
            return saveCountry!=null && saveCountry.getId()>0   ;
        }catch (Exception e){
            return false;
        }
    }
}
