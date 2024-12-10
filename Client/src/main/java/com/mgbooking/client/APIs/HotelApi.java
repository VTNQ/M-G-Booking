package com.mgbooking.client.APIs;

import com.mgbooking.client.DTO.Hotel.HotelListDto;
import com.mgbooking.client.DTO.Hotel.HotelUpdateDTO;
import com.mgbooking.client.DTO.Hotel.ImageListDto;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.springframework.data.repository.query.Param;
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
    @GET("/Hotel/FindById/{id}")
    Call<HotelUpdateDTO> FindHotelById(
            @Header("Authorization") String auth,
            @Path("id") int id
    );
    @POST("/Hotel/UpdateHotel")
    @Multipart
    Call<Object>UpdateHotel(@Header("Authorization")String auth,@Part("id")RequestBody id,
            @Part("name")RequestBody name,@Part("decription")RequestBody decription,@Part("address")RequestBody address,
            @Part("cityId")RequestBody cityId,@Part("district_id")RequestBody district_id,@Part("ownerId")RequestBody ownerId,@Part MultipartBody.Part image);
    @GET("/Hotel/FindImage/{id}")
    Call<List<ImageListDto>>FindImages(@Path("id")int id,@Header("Authorization") String auth);
    @POST("/Hotel/UpdateMultipleImage/{id}")
    Call<Object>UpdateMultipleImage(@Header("Authorization")String auth,@Param("id")int id,@Part List<MultipartBody.Part>images);
}
