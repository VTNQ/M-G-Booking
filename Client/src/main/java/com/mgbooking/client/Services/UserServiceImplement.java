package com.mgbooking.client.Services;

import com.mgbooking.client.APIs.ApiClient;
import com.mgbooking.client.APIs.UserApi;
import com.mgbooking.client.DTO.RegisterUser;
import com.mgbooking.client.DTO.ResultApi;
import org.springframework.stereotype.Service;
import retrofit2.Response;

@Service
public class UserServiceImplement implements UserService{

    @Override
    public boolean RegisterUser(RegisterUser registerUser) {
        try {
            UserApi userApi= ApiClient.getRetrofit().create(UserApi.class);
            Response<ResultApi>response=userApi.RegisterUser(registerUser).execute();
            if(response.isSuccessful()){
                return response.body().isResult();
            }else{
                return false;
            }
        }catch (Exception e) {
            return false;
        }
    }
}
