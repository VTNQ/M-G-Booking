package com.mgbooking.server.Services;

import com.mgbooking.server.Entities.District;
import com.mgbooking.server.Repositories.DistrictRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DistrictServiceImplement implements DistrictService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DistrictRepository districtRepository;
    @Override
    public List<District> GetDistricts(int id) {
        try {
            return modelMapper.map(districtRepository.findDistrictByCity(id),new TypeToken<List<District>>(){}.getType());
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
