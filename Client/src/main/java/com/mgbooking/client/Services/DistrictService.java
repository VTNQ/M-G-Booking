package com.mgbooking.client.Services;

import com.mgbooking.client.DTO.District.District;

import java.util.List;

public interface DistrictService {
    public List<District> getDistricts(String token,int id);
}
