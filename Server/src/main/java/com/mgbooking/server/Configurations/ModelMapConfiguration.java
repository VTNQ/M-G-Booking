package com.mgbooking.server.Configurations;

import com.mgbooking.server.DTOS.RegisterUser;
import com.mgbooking.server.Entities.Account;
import org.modelmapper.ModelMapper;
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
        return modelMapper;
    }
}
