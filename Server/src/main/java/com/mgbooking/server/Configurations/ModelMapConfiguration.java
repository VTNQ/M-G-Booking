package com.mgbooking.server.Configurations;

import com.mgbooking.server.DTOS.*;
import com.mgbooking.server.Entities.Account;
import com.mgbooking.server.Entities.Airport;
import com.mgbooking.server.Entities.City;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

        return modelMapper;
    }
}
