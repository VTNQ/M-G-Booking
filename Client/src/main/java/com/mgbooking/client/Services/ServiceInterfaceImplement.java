package com.mgbooking.client.Services;

import com.mgbooking.client.APIs.ApiClient;
import com.mgbooking.client.APIs.ServiceApi;
import com.mgbooking.client.DTO.Service.Service;
import com.mgbooking.client.DTO.Service.ServiceDTO;
import retrofit2.Response;

import java.util.List;
@org.springframework.stereotype.Service
public class ServiceInterfaceImplement implements ServiceInterface {
    @Override
    public List<Service> FindAllServices(String token,int id) {
        try {
            ServiceApi Service= ApiClient.getRetrofit().create(ServiceApi.class);
            Response<List<Service>>response=Service.FindAll("Bearer " + token,id).execute();
            if(response.isSuccessful()){
                return response.body();
            }else{
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object AddService(String token,ServiceDTO service) {
        try {
            ServiceApi Service= ApiClient.getRetrofit().create(ServiceApi.class);
            Object object=Service.add("Bearer " + token,service).execute().body();
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
    public ServiceDTO FindService(String token, int id) {
        try {
            ServiceApi Service= ApiClient.getRetrofit().create(ServiceApi.class);
            Response<ServiceDTO>response=Service.findById("Bearer " + token,id).execute();
            if(response.isSuccessful()){
                return response.body();
            }else{
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
