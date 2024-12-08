package com.mgbooking.server.Controllers;


import com.mgbooking.server.DTOS.Hotel.HotelDTO;
import com.mgbooking.server.DTOS.Hotel.HotelListDto;
import com.mgbooking.server.DTOS.Hotel.HotelUpdateDTO;
import com.mgbooking.server.Entities.District;
import com.mgbooking.server.Services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping(value = "")
    public ResponseEntity<List<HotelListDto>> getAllHotels(@RequestHeader(value =  "Authorization",required = true)String authorizationHeader) {
    try {
        return new ResponseEntity<List<HotelListDto>>(hotelService.FindAllHotels(authorizationHeader), HttpStatus.OK);
    }catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<List<HotelListDto>>(HttpStatus.BAD_REQUEST);
    }
    }
    @GetMapping(value = "FindById/{id}")
    public ResponseEntity<HotelUpdateDTO>GetHotelById(@PathVariable("id") int id){
        try{
            return new ResponseEntity<HotelUpdateDTO>(hotelService.findHotels(id), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<HotelUpdateDTO>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "addHotel")
    public ResponseEntity<Object> addHotel(@ModelAttribute HotelDTO hotel) {
        Map<String, Object> response = new LinkedHashMap<>();
        if(hotelService.addHotel(hotel)) {
            response.put("status",200);
            response.put("message","Hotel added successfully");
            return  ResponseEntity.ok(response);
        }else{
            response.put("status",400);
            response.put("message","Hotel added failed");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
