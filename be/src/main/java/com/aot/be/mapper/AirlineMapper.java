package com.aot.be.mapper;

import com.aot.be.model.dtos.reponse.AirlineResponse;
import com.aot.be.model.dtos.request.AirlineRequest;
import com.aot.be.model.entities.Airline;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AirlineMapper {

    @Mapping(target = "createdBy", source = "userId")
    @Mapping(target = "updatedBy", source = "userId")
    Airline toEntity(AirlineRequest airlineRequest,Long userId);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "updatedBy", source = "userId")
    void updateEntity(AirlineRequest airlineRequest, @MappingTarget Airline airline,Long userId);

    AirlineResponse toResponse(Airline airline);
}
