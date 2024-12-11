package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.District.District;
import com.mgbooking.client.DTO.District.DistrictDTO;

import java.util.List;

public interface DistrictService {
    public List<District> getDistricts(String token,int id);
    public Object addDistrict(String token,DistrictDTO districtDTO);
    public DistrictDTO getDistrict(String token,int id);
    public Object UpdateDistrict(String token,DistrictDTO districtDTO);
}
