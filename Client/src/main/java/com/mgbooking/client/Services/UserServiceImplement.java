package com.mgbooking.client.Services;

import com.mgbooking.client.APIs.ApiClient;
import com.mgbooking.client.APIs.LoginApi;
import com.mgbooking.client.APIs.UserApi;
import com.mgbooking.client.DTO.Account.AccountAdmin;
import com.mgbooking.client.DTO.Account.RegisterAdmin;
import com.mgbooking.client.DTO.AccountDto;
import com.mgbooking.client.DTO.RegisterUser;
import com.mgbooking.client.DTO.ResultApi;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.util.List;

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

    @Override
    public Object UpdateAccount(String token, AccountDto accountDto) {
        try {
            UserApi userApi= ApiClient.getRetrofit().create(UserApi.class);
            Object object=userApi.UpdateAccount("Bearer " + token,accountDto).execute().body();
            if(object!=null){
                return object;
            }else {
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object RegisterAdmin(String token, RegisterAdmin registerAdmin) {
        try {
            UserApi userApi=ApiClient.getRetrofit().create(UserApi.class);
            Object object=userApi.CreateAdminAccount("Bearer " + token,registerAdmin).execute().body();
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
    public List<AccountAdmin> GetAccountAdmin(String token) {
        try {
            UserApi userApi=ApiClient.getRetrofit().create(UserApi.class);
            Response<List<AccountAdmin>>response=userApi.GetAccountAdmin("Bearer " + token).execute();
            if(response.isSuccessful()){
                return response.body();
            }else{
                return null;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }


}
