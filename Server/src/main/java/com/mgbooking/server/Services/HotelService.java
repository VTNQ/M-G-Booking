package com.mgbooking.server.Services;

import com.mgbooking.server.DTOS.Hotel.HotelDTO;
import com.mgbooking.server.DTOS.Hotel.HotelListDto;
import com.mgbooking.server.DTOS.Hotel.HotelUpdateDTO;

import java.util.List;


public interface HotelService {
    public boolean addHotel(HotelDTO hotel);
    public List<HotelListDto>FindAllHotels(String token);
    public HotelUpdateDTO findHotels(int id);
}
