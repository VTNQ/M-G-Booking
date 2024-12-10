package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.Hotel.HotelDTO;
import com.mgbooking.server.DTOS.Hotel.HotelListDto;
import com.mgbooking.server.DTOS.Hotel.HotelUpdateDTO;
import com.mgbooking.server.DTOS.Hotel.ImageListDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface HotelService {
    public boolean addHotel(HotelDTO hotel);
    public List<HotelListDto>FindAllHotels(String token);
    public HotelUpdateDTO findHotels(int id);
    public boolean updateHotel(HotelUpdateDTO hotel, MultipartFile file);
    public List<ImageListDto>FindImages(int id);
    public boolean DeleteHotel(int id);
    public boolean UpdateMultipleImages(int id,List<MultipartFile> files);
}
