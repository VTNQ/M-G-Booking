package com.mgbooking.server.Configurations;

import com.mgbooking.server.DTOS.*;
import com.mgbooking.server.DTOS.Account.AccountAdmin;
import com.mgbooking.server.DTOS.Account.RegisterAdmin;
import com.mgbooking.server.DTOS.District.DistrictDTO;
import com.mgbooking.server.DTOS.Service.ServiceDTO;
import com.mgbooking.server.Entities.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Configuration
public class ModelMapConfiguration {
    @Bean
    public ModelMapper modelMap() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(RegisterUser.class, Account.class).addMappings(mapper->
                mapper.map(RegisterUser::getFull_name,Account::setFullName)
                );
        modelMapper.typeMap(City.class, CityDTO.class).addMappings(mapper->
                mapper.map(City::getCountry,CityDTO::setCountry_id)
                );
        modelMapper.typeMap(Airport.class,AirPortList.class).addMappings(mapper->
                mapper.map(Airport::getCity,AirPortList::setCity)

                );

        modelMapper.typeMap(Airport.class, AirPortDTO.class).addMappings(mapper->
                mapper.map(Airport::getCity,AirPortDTO::setCity_id)
                );
        modelMapper.typeMap(Flight.class, FlightDTO.class).addMappings(mapper->
                {
                    mapper.map(Flight::getDepartureTime,FlightDTO::setDepartureTime);
                    mapper.map(Flight::getArrivalTime,FlightDTO::setArrivalTime);
                   mapper.map(Flight::getAirline,FlightDTO::setAirline_id);
                   mapper.map(Flight::getDepartureAirport,FlightDTO::setDeparture_airport);
                   mapper.map(Flight::getArrivalAirport,FlightDTO::setArrival_airport);
                }

                );
        modelMapper.typeMap(DetailFlight.class,DetailFlightDTO.class).addMappings(mapper->{
            mapper.map(DetailFlight::getId,DetailFlightDTO::setId);
            mapper.map(DetailFlight::getIdFlight,DetailFlightDTO::setIdFlight);
        });
        modelMapper.typeMap(District.class,DistrictDTO.class).addMappings(mapper->{
            mapper.map(district->district.getCity().getId(),DistrictDTO::setDistrictId);
        });
        modelMapper.typeMap(Service.class, ServiceDTO.class).addMappings(mapper->{
            mapper.map(service->service.getHotel().getId(),ServiceDTO::setHotel_id);
        });
        return modelMapper;
    }


}
