package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.*;
import com.mgbooking.server.Entities.*;
import com.mgbooking.server.Repositories.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private Environment environment;
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
    public Page<FlightPaginateDTo> FindAllByCountry(Pageable pageable, int id,String name) {
        return modelMapper.map(flightRepository.FindAllFlights(pageable,id,name),new TypeToken<Page<FlightListDto>>(){}.getType());
    }
    private Instant convertToInstant(String timeString) {
        if (timeString == null || timeString.isEmpty()) {
            throw new IllegalArgumentException("Time string cannot be null or empty");
        }

        // Parse the string to LocalDateTime (no time zone, just local date and time)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(timeString, formatter);

        // Convert LocalDateTime to ZonedDateTime in Ho Chi Minh City time zone (UTC +7)
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));

        // Convert the ZonedDateTime to Instant
        return zonedDateTime.toInstant();
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
            flight.setArrivalTime(convertToInstant(flightListDto.getArrival_time()));
            flight.setDepartureTime(convertToInstant(flightListDto.getDeparture_time()));
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
    public List<ResultFlightDTO> SearchFlight(int departureAirport, int arrivalAirport, LocalDate departureTime,String TypeFlight) {
        String flightUrl = environment.getProperty("FlightUrl");
        List<ResultFlightDTO> flight=flightRepository.findFlightsByAirportsAndDepartureTime(departureAirport,arrivalAirport,departureTime,TypeFlight);

        return modelMapper.map(flightRepository.findFlightsByAirportsAndDepartureTime(departureAirport,arrivalAirport,departureTime,TypeFlight),new TypeToken<List<ResultFlightDTO>>(){}.getType());
    }
    @Override
    public BigDecimal FindPrice(LocalDate departureTime) {
        try {
            // Sử dụng modelMapper để chuyển đổi kết quả từ repository
            return modelMapper.map(flightRepository.FindPrice(departureTime), new TypeToken<BigDecimal>(){}.getType());
        } catch (Exception ex) {
            // Ghi log lỗi để dễ dàng kiểm tra
            System.err.println("Error finding price: " + ex.getMessage());

            return BigDecimal.ZERO;
        }
    }

    @Override
    public List<ResultFlightDTO> SearchFlightAllDto(int departureAirport, int arrivalAirport, LocalDate departureTime, LocalDate ArrivalTime, String TypeFlight) {
        String flightUrl = environment.getProperty("FlightUrl");
        List<ResultFlightDTO> flight=flightRepository.SearchFindFlightAll(departureAirport,arrivalAirport,departureTime,ArrivalTime,TypeFlight);

        return modelMapper.map(flight,new TypeToken<List<ResultFlightDTO>>(){}.getType());
    }


}
