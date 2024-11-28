package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.*;
import com.mgbooking.server.Entities.*;
import com.mgbooking.server.Repositories.AirlineRepository;
import com.mgbooking.server.Repositories.AirportRepository;
import com.mgbooking.server.Repositories.DetailFlightRepository;
import com.mgbooking.server.Repositories.FlightRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImplement implements FlightService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private DetailFlightRepository flightDetailRepository;
    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private DetailFlightRepository detailFlightRepository;

    @Override
    public boolean CreateFlight(FlightDTO flightDTO) {
        try {
            Airline airline = airlineRepository.findById(flightDTO.getAirline_id())
                    .orElseThrow(() -> new RuntimeException("Airline not found"));
            Airport departure_airport=airportRepository.findById(flightDTO.getDeparture_airport())
                    .orElseThrow(() -> new RuntimeException("Departure Airport not found"));
            Airport arrival_airport=airportRepository.findById(flightDTO.getArrival_airport())
                    .orElseThrow(() -> new RuntimeException("Arrival Airport not found"));

            Flight flight=modelMapper.map(flightDTO,Flight.class);
            flight.setArrivalTime(flightDTO.getArrivalInstant());
            flight.setDepartureTime(flightDTO.getDepartureInstant());
            flight.setAirline(airline);
            flight.setDepartureAirport(departure_airport);
            flight.setArrivalAirport(arrival_airport);
            Flight insertDto=flightRepository.save(flight);
            DetailFlight detailFlight=new DetailFlight();
            for (DetailFlightDTO detailflightarray : flightDTO.getDetailFlights()) {
                try {
                    detailFlight.setType(detailflightarray.getType());
                    detailFlight.setIdFlight(insertDto);
                    detailFlight.setPrice(detailflightarray.getPrice());
                    detailFlight.setQuantity(detailflightarray.getQuantity());
                    flightDetailRepository.save(detailFlight);
                    detailFlight=new DetailFlight();
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }


            return insertDto!=null && insertDto.getId()>0 ;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public FlightListDto GetFlight(int id) {


        return modelMapper.map(flightRepository.findFlightWithDetails(id),new TypeToken<FlightListDto>(){}.getType());
    }

    @Override
    public Page<FlightPaginateDTo> FindAllByCountry(Pageable pageable, int id) {
        return modelMapper.map(flightRepository.FindAllFlights(pageable,id),new TypeToken<Page<FlightListDto>>(){}.getType());
    }

    @Override
    public boolean UpdateInformationFlight(FlightListDto flightListDto) {
        try {
            Airline airline=airlineRepository.findById(flightListDto.getAirline_id())
                    .orElseThrow(() -> new RuntimeException("Airline not found"));
            Airport departure_airport=airportRepository.findById(flightListDto.getDeparture_airport())
                    .orElseThrow(() -> new RuntimeException("Departure Airport not found"));
            Airport arrival_airport=airportRepository.findById(flightListDto.getArrival_airport())
                    .orElseThrow(() -> new RuntimeException("Arrival Airport not found"));
            Flight flight=modelMapper.map(flightListDto,Flight.class);
            flight.setArrivalTime(flightListDto.getArrivalInstant());
            flight.setDepartureTime(flightListDto.getDepartureInstant());
            flight.setAirline(airline);
            flight.setDepartureAirport(departure_airport);
            flight.setArrivalAirport(arrival_airport);
            Flight updateDto=flightRepository.save(flight);
            return updateDto!=null && updateDto.getId()>0;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<FlightListDto> SearchFlight(SearchFlightDTO searchFlightDTO) {
        return modelMapper.map(flightRepository.findFlightsByAirportsAndDepartureTime(searchFlightDTO.getDepartureAirport(),searchFlightDTO.getArrivalAirport(),searchFlightDTO.getDepartureTime()),new TypeToken<List<FlightListDto>>(){}.getType());
    }


}
