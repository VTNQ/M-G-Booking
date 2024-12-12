package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.Service.Service;
import com.mgbooking.client.DTO.Service.ServiceDTO;

import java.util.List;

public interface ServiceInterface {
    public List<Service>FindAllServices(String token,int id);
    public Object AddService(String token,ServiceDTO service);
    public ServiceDTO FindService(String token,int id);
}
