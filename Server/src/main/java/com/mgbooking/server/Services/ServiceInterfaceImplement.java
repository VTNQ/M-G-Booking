package com.mgbooking.server.Services;
import com.mgbooking.server.DTOS.Service.ServiceDTO;
import com.mgbooking.server.Entities.Country;
import com.mgbooking.server.Entities.Hotel;
import com.mgbooking.server.Repositories.HotelRepository;
import com.mgbooking.server.Repositories.ServiceRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceInterfaceImplement implements ServiceInterface {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Override
    public List<com.mgbooking.server.Entities.Service> FindAll(int id) {
        return modelMapper.map(serviceRepository.FindAll(id),new TypeToken<List<com.mgbooking.server.Entities.Service>>(){}.getType());
    }

    @Override
    public boolean addService(ServiceDTO service) {
        try {
           Hotel hotel = hotelRepository.findById(service.getHotel_id())
                    .orElseThrow(() -> new RuntimeException("Hotel not found"));
            com.mgbooking.server.Entities.Service serviceEntity=modelMapper.map(service,com.mgbooking.server.Entities.Service.class);
            serviceEntity.setHotel(hotel);
            com.mgbooking.server.Entities.Service insertedService=serviceRepository.save(serviceEntity);
            return insertedService!=null && insertedService.getId()>0;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean DeleteService(int id) {
        try {
            serviceRepository.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ServiceDTO FindById(int id) {
        return modelMapper.map(serviceRepository.findById(id),new TypeToken<com.mgbooking.server.Entities.Service>(){}.getType());
    }
}
