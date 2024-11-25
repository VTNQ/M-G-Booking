package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.*;
import com.mgbooking.server.Entities.Airport;
import com.mgbooking.server.Entities.City;
import com.mgbooking.server.Repositories.AirportRepository;
import com.mgbooking.server.Repositories.CityRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AirPortServiceImplement implements  AirPortService{
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public boolean AddAirPort(AirPortDTO airportDTO) {
        try {
            City City=cityRepository.findById(airportDTO.getCity_id())
                    .orElseThrow(() -> new RuntimeException("City not found"));
            Airport airport=modelMapper.map(airportDTO, Airport.class);
            airport.setCity(City);
            Airport insertAiport=airportRepository.save(airport);
            return insertAiport!=null && insertAiport.getId()>0;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Page<AirPortList> GetAll(int id, Pageable pageable) {

        return modelMapper.map(airportRepository.findAll(id,pageable),new TypeToken<Page<Airport>>(){}.getType());
    }

    @Override
    public AirPortDTO FindById(int id) {
        return modelMapper.map(airportRepository.findById(id),new TypeToken<AirPortDTO>(){}.getType());
    }
}
