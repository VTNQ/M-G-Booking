package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.DetailFlightDTO;
import com.mgbooking.server.Entities.DetailFlight;
import com.mgbooking.server.Entities.Flight;
import com.mgbooking.server.Repositories.DetailFlightRepository;
import com.mgbooking.server.Repositories.FlightRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DetalFlightServiceImplement implements DetailFlightService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FlightRepository flightRepository1;
    @Autowired
    private DetailFlightRepository flightRepository;
    @Override
    public List<DetailFlightDTO> FindAll(int id) {
        return modelMapper.map(flightRepository.findDetailFlightsByFlightId(id),new TypeToken<List<DetailFlightDTO>>(){}.getType());
    }

    @Override
    public boolean UpdateDetailFlight(List<DetailFlightDTO> detailFlight) {
        try {
            for (DetailFlightDTO detailFlightDTO : detailFlight) {
                Flight flight=flightRepository1.findById(detailFlightDTO.getIdFlight()) .orElseThrow(() -> new RuntimeException("Flight not found"));
                DetailFlight flightDTO=modelMapper.map(detailFlightDTO,DetailFlight.class);
                flightDTO.setIdFlight(flight);
                flightRepository.save(flightDTO);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
