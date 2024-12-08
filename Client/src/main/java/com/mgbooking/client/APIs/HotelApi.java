package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.Hotel.HotelListDto;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface HotelApi {
    @POST("/Hotel/addHotel")
    @Multipart
    Call<Object>addHotel(@Header("Authorization")String auth,
                         @Part("name")RequestBody name, @Part("address") RequestBody address,
                         @Part("city_id")RequestBody city_id, @Part("decription")RequestBody decription,
                         @Part("district_id") RequestBody district_id, @Part("ownerId")RequestBody ownerId,
                         @Part MultipartBody.Part image,@Part List<MultipartBody.Part>images);
    @GET("/Hotel")
    Call<List<HotelListDto>>FindAllHotels(@Header("Authorization")String auth);
}
