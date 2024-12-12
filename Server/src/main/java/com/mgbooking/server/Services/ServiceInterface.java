package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.Service.ServiceDTO;
import com.mgbooking.server.Entities.Service;

import java.util.List;

public interface ServiceInterface {
    public List<Service>FindAll(int id);
    public boolean addService(ServiceDTO service);
    public boolean DeleteService(int id);
    public ServiceDTO FindById(int id);
}
