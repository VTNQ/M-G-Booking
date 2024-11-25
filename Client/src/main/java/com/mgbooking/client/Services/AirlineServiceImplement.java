package com.mgbooking.client.Services;

import com.mgbooking.client.APIs.AirlinesApi;
import com.mgbooking.client.APIs.ApiClient;
import com.mgbooking.client.DTO.*;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.Response;

import java.util.List;

@Service
public class AirlineServiceImplement implements AirlineService{
    @Override
    public Object CreateFlight(AirlineDTO flightDTO, String token) {
        try {
            // Convert the fields to RequestBody
            RequestBody nameRequestBody = RequestBody.create(MediaType.parse("text/plain"), flightDTO.getName());
            RequestBody countryIdRequestBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(flightDTO.getCountry_id()));

            // Convert MultipartFile to MultipartBody.Part directly
            MultipartBody.Part imagePart = null;
            if (flightDTO.getImage() != null) {
                // Convert MultipartFile to RequestBody
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), flightDTO.getImage().getBytes());

                // Create MultipartBody.Part with the name "image" and the file
                imagePart = MultipartBody.Part.createFormData("image", flightDTO.getImage().getOriginalFilename(), requestBody);
            }


            AirlinesApi airlinesApi = ApiClient.getRetrofit().create(AirlinesApi.class);
            Object object = airlinesApi.AddFlight("Bearer " + token, nameRequestBody, countryIdRequestBody, imagePart).execute().body();

            if (object != null) {
                return object;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ListFlightDto> findAll(String token) {
        try {
            AirlinesApi airlinesApi=ApiClient.getRetrofit().create(AirlinesApi.class);
            Response<List<ListFlightDto>> response=airlinesApi.getFlight("Bearer " +token).execute();
            if(response.isSuccessful()){
                return response.body();
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UpdateFlightDTO FindFlight(String token, int id) {
        try {
            AirlinesApi airlinesApi=ApiClient.getRetrofit().create(AirlinesApi.class);
            Response<UpdateFlightDTO>response=airlinesApi.FindFlight("Bearer " +token,id).execute();
            if(response.isSuccessful()){
                return response.body();
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean UpdateFlight(UpdateFlightDTO updateFlightDTO, String token,MultipartFile multipartFile) {
        try {
            RequestBody id = RequestBody.create(String.valueOf(updateFlightDTO.getId()), MediaType.parse("text/plain"));
            RequestBody name = RequestBody.create(updateFlightDTO.getName(), MediaType.parse("text/plain"));
            RequestBody idCountry = RequestBody.create(String.valueOf(updateFlightDTO.getIdCountry()), MediaType.parse("text/plain"));


            RequestBody requestFile = RequestBody.create(multipartFile.getBytes(), MediaType.parse("image/*"));
            MultipartBody.Part image = MultipartBody.Part.createFormData("imageForm", multipartFile.getOriginalFilename(), requestFile);
            AirlinesApi airlinesApi=ApiClient.getRetrofit().create(AirlinesApi.class);
            Response<ResultApi> response = airlinesApi.Update("Bearer " + token, id, name, idCountry, image).execute();
            if(response.isSuccessful()){
                return response.body().isResult();
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ListFlightDto> FindAirlineByCountry(String token, int id) {
        try {
            AirlinesApi airlinesApi=ApiClient.getRetrofit().create(AirlinesApi.class);
            Response<List<ListFlightDto>>response=airlinesApi.FindAirlineByCountry("Bearer " +token,id).execute();
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
