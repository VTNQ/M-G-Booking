package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.District.DistrictDTO;
import com.mgbooking.server.Entities.City;
import com.mgbooking.server.Entities.District;
import com.mgbooking.server.Repositories.CityRepository;
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
    @Autowired
    private CityRepository cityRepository;
    @Override
    public List<District> GetDistricts(int id) {
        try {
            return modelMapper.map(districtRepository.findDistrictByCity(id),new TypeToken<List<District>>(){}.getType());
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean AddDistrict(DistrictDTO districtDTO) {
        try {
            City city = cityRepository.findById(districtDTO.getDistrictId())
                    .orElseThrow(() -> new RuntimeException("City not found"));
            District district = modelMapper.map(districtDTO, District.class);
            district.setCity(city);
            District insertDistrict=districtRepository.save(district);
            return  insertDistrict!=null && insertDistrict.getId()>0;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public DistrictDTO GetDistrictById(int id) {
        return modelMapper.map(districtRepository.findById(id),new TypeToken<DistrictDTO>(){}.getType());
    }
}
