package com.mgbooking.server.Configurations;

import com.mgbooking.server.DTOS.CityDTO;
import com.mgbooking.server.DTOS.RegisterUser;
import com.mgbooking.server.Entities.Account;
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
        return modelMapper;
    }
}
