package com.mgbooking.client.Services;


import com.mgbooking.client.DTO.Hotel.HotelDTO;
import com.mgbooking.client.DTO.Hotel.HotelListDto;
import com.mgbooking.client.DTO.Hotel.HotelUpdateDTO;
import com.mgbooking.client.DTO.Hotel.ImageListDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HotelService {
public Object addHotel(String token,HotelDTO hotel);
public List<HotelListDto>FindAllHotels(String token);
public HotelUpdateDTO FindHotelById(String token,int id);
public Object UpdateHotel(String token, HotelUpdateDTO hotel, MultipartFile file);
public List<ImageListDto>FindAllImages(String token,int id);
}
