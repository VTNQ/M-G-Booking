package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.District.DistrictDTO;
import com.mgbooking.server.Entities.District;

import java.util.List;

public interface DistrictService {
    public List<District>GetDistricts(int id);
    public boolean AddDistrict(DistrictDTO districtDTO);
    public DistrictDTO GetDistrictById(int id);
}
