package com.mgbooking.client.Services;

import com.mgbooking.client.APIs.ApiClient;
import com.mgbooking.client.APIs.DistrictApi;
import com.mgbooking.client.DTO.District.District;
import com.mgbooking.client.DTO.District.DistrictDTO;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.util.List;
@Service
public class DistrictServiceImplement implements DistrictService {
    @Override
    public List<District> getDistricts(String token, int id) {
        try {
            DistrictApi districtApi= ApiClient.getRetrofit().create(DistrictApi.class);
            Response<List<District>>response=districtApi.getDistrict("Bearer " +token,id).execute();
            if(response.isSuccessful()){
                return  response.body();
            }else{
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object addDistrict(String token,DistrictDTO districtDTO) {
        try {
            DistrictApi districtApi= ApiClient.getRetrofit().create(DistrictApi.class);
            Object object=districtApi.AddDistrict("Bearer " + token,districtDTO).execute().body();
            if(object!=null){
                return object;
            }else{
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DistrictDTO getDistrict(String token, int id) {
        try {
            DistrictApi districtApi= ApiClient.getRetrofit().create(DistrictApi.class);
            Response<DistrictDTO>response=districtApi.FindByDistrict("Bearer " +token,id).execute();
            if(response.isSuccessful()){
                return  response.body();
            }else{
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object UpdateDistrict(String token, DistrictDTO districtDTO) {
        try {
            DistrictApi districtApi= ApiClient.getRetrofit().create(DistrictApi.class);
            Object object=districtApi.UpdateDistrict("Bearer " + token,districtDTO).execute().body();
            if(object!=null){
                return object;
            }else{
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
