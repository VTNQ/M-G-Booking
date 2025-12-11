package com.aot.be.services;

import com.aot.be.model.dtos.reponse.AirlineResponse;
import com.aot.be.model.dtos.request.AirlineRequest;
import com.aot.be.model.entities.Airline;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface AirlineService extends BaseService<AirlineResponse,Integer> {
    void create(AirlineRequest request, MultipartFile file);

    void update(Integer id,AirlineRequest request, MultipartFile file);

    void changeStatus(Integer id,Integer status);
}
