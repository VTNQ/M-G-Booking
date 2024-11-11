package com.mgbooking.client.Services;

import com.mgbooking.client.APIs.ApiClient;
import com.mgbooking.client.APIs.OwnerApi;
import com.mgbooking.client.DTO.RegisterOwnerDto;
import com.mgbooking.client.DTO.ResultApi;
import org.springframework.stereotype.Service;
import retrofit2.Response;
@Service
public class OwnerServiceimplement implements OwnerService {
    @Override
    public boolean RegisterOwner(RegisterOwnerDto registerOwnerDto) {
        try{
            OwnerApi ownerApi= ApiClient.getRetrofit().create(OwnerApi.class);
            Response<ResultApi> response=ownerApi.RegisterOwner(registerOwnerDto).execute();
            if(response.isSuccessful()){
                return response.body().isResult();
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
}
