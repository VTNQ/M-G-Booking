package com.mgbooking.server.Configurations;

import com.mgbooking.server.DTOS.*;
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
        modelMapper.addMappings(new PropertyMap<City, CityDTO>() {
            @Override
            protected void configure() {
                map().setCountry_id(source.getCountry().getId());
            }
        });
        modelMapper.addMappings(new PropertyMap<Airport, AirPortList>() {
            @Override
            protected void configure() {
                map().setCity(source.getCity());
            }
        });
        modelMapper.addMappings(new PropertyMap<Airport, AirPortDTO>() {
            @Override
            protected void configure() {
                map().setCity_id(source.getCity().getId());
            }
        });
        modelMapper.addMappings(new PropertyMap<Flight, FlightListDto>() {
            @Override
            protected void configure() {
                map(source.getDepartureTime(), destination.getDeparture_time());
                map(source.getArrivalTime(), destination.getArrival_time());
                map().setAirline_id(source.getAirline().getId());
                map().setDeparture_airport(source.getDepartureAirport().getId());
                map().setArrival_airport(source.getArrivalAirport().getId());
            }
        });
        modelMapper.addMappings(new PropertyMap<DetailFlight, DetailFlightDTO>() {
            @Override
            protected void configure() {
           map().setId(source.getId());
            }
        });

        return modelMapper;
    }


}
