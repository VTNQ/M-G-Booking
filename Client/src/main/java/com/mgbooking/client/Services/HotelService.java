package com.mgbooking.client.Services;


import com.mgbooking.client.DTO.Hotel.HotelDTO;
import com.mgbooking.client.DTO.Hotel.HotelListDto;

import java.util.List;

public interface HotelService {
public Object addHotel(String token,HotelDTO hotel);
public List<HotelListDto>FindAllHotels(String token);
}
