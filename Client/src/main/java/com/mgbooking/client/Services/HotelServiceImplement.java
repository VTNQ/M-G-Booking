package com.mgbooking.client.Services;


import com.mgbooking.client.APIs.ApiClient;
import com.mgbooking.client.APIs.HotelApi;
import com.mgbooking.client.DTO.Hotel.HotelDTO;
import com.mgbooking.client.DTO.Hotel.HotelListDto;
import com.mgbooking.client.DTO.Hotel.HotelUpdateDTO;
import com.mgbooking.client.DTO.Hotel.ImageListDto;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class HotelServiceImplement implements HotelService {

    @Override
    public Object addHotel(String token, HotelDTO hotel) {
        try {
            RequestBody nameRequestBody = RequestBody.create(MediaType.parse("text/plain"), hotel.getName());
            RequestBody addressRequestBody = RequestBody.create(MediaType.parse("text/plain"), hotel.getAddress());
            RequestBody descriptionRequestBody = RequestBody.create(MediaType.parse("text/plain"), hotel.getDecription());
            RequestBody idCity=RequestBody.create(MediaType.parse("text/plain"),String.valueOf(hotel.getCity_id()));
            RequestBody districtId=RequestBody.create(MediaType.parse("text/plain"),String.valueOf(hotel.getDistrict_id()));
            RequestBody ownerId=RequestBody.create(MediaType.parse("text/plain"),String.valueOf(hotel.getOwnerId()));
            // Initialize MultipartBody.Part for the image if it exists
            MultipartBody.Part imagePart = null;
            if (hotel.getImage() != null) {
                RequestBody imageRequestBody = RequestBody.create(MediaType.parse("image/*"), hotel.getImage().getBytes());
                imagePart = MultipartBody.Part.createFormData("image", hotel.getImage().getOriginalFilename(), imageRequestBody);
            }
            List<MultipartBody.Part>imagesParts = new ArrayList<>();
            if(hotel.getImage()!=null && !hotel.getImage().isEmpty()){
                for(MultipartFile file :hotel.getImages()){
                    MultipartBody.Part part = createImagePart("images", file);
                   imagesParts.add(part);
                }
            }
            // Create the HotelApi interface
            HotelApi hotelApi = ApiClient.getRetrofit().create(HotelApi.class);

            // Execute the API call and get the response
           Object call = hotelApi.addHotel(
                    "Bearer " + token,
                   nameRequestBody,
                   addressRequestBody,
                   idCity,
                   descriptionRequestBody,
                   districtId,
                   ownerId,
                   imagePart,
                   imagesParts

            ).execute().body();
            if(call != null) {
                return call;
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<HotelListDto> FindAllHotels(String token) {
        try {
            HotelApi hotelApi = ApiClient.getRetrofit().create(HotelApi.class);
            Response<List<HotelListDto>> response=hotelApi.FindAllHotels("Bearer " + token).execute();
            if(response.isSuccessful()) {
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
    public HotelUpdateDTO FindHotelById(String token, int id) {
        try {
                HotelApi hotelApi=ApiClient.getRetrofit().create(HotelApi.class);
            Response<HotelUpdateDTO>response=hotelApi.FindHotelById("Bearer " + token,id).execute();
            if(response.isSuccessful()) {
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
    public Object UpdateHotel(String token, HotelUpdateDTO hotel, MultipartFile file) {
        try {
            RequestBody idRequestBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(hotel.getId()));
            RequestBody nameRequestBody = RequestBody.create(MediaType.parse("text/plain"), hotel.getName());
            RequestBody addressRequestBody = RequestBody.create(MediaType.parse("text/plain"), hotel.getAddress());
            RequestBody descriptionRequestBody = RequestBody.create(MediaType.parse("text/plain"), hotel.getDecription());
            RequestBody cityIdRequestBody=RequestBody.create(MediaType.parse("text/plain"),String.valueOf(hotel.getCityId()));
            RequestBody districtIdRequestBody=RequestBody.create(MediaType.parse("text/plain"),String.valueOf(hotel.getDistrict_id()));
            RequestBody OwnerIdRequestBody=RequestBody.create(MediaType.parse("text/plain"),String.valueOf(hotel.getOwnerId()));
            RequestBody requestFile = RequestBody.create(file.getBytes(), MediaType.parse("image/*"));
            MultipartBody.Part image = MultipartBody.Part.createFormData("imageForm", file.getOriginalFilename(), requestFile);
            HotelApi hotelApi = ApiClient.getRetrofit().create(HotelApi.class);
            Object call=hotelApi.UpdateHotel(
                    "Bearer " + token,
                    idRequestBody,
                    nameRequestBody,
                    descriptionRequestBody,
                    addressRequestBody,
                    cityIdRequestBody,
                    districtIdRequestBody,
                    OwnerIdRequestBody,
                    image
            ).execute().body();
            if(call != null) {
                return call;
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ImageListDto> FindAllImages(String token, int id) {
        try {
            HotelApi hotelApi = ApiClient.getRetrofit().create(HotelApi.class);
            Response<List<ImageListDto>>response=hotelApi.FindImages(id, "Bearer " + token).execute();
            if(response.isSuccessful()) {
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
    public Object UpdateMultipleImages(String token, int id, List<MultipartFile> files) {
        try {
            HotelApi hotelApi=ApiClient.getRetrofit().create(HotelApi.class);
            List<MultipartBody.Part>imagesParts = new ArrayList<>();
            if(files!=null && !files.isEmpty()){
                for(MultipartFile file :files){
                    MultipartBody.Part part = createImagePart("MultiImage", file);
                    imagesParts.add(part);
                }
            }

            Object call=hotelApi.UpdateMultipleImage("Bearer " + token,id,imagesParts).execute().body();
            if(call != null) {
                return call;
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private MultipartBody.Part createImagePart(String partName, MultipartFile file) {
        try {
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), file.getBytes());
            return MultipartBody.Part.createFormData(partName, file.getOriginalFilename(), fileBody);
        } catch (IOException e) {
            throw new RuntimeException("Error creating image part: " + e.getMessage(), e);
        }
    }
}
