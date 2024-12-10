package com.mgbooking.server.Controllers;


import com.mgbooking.server.DTOS.Hotel.HotelDTO;
import com.mgbooking.server.DTOS.Hotel.HotelListDto;
import com.mgbooking.server.DTOS.Hotel.HotelUpdateDTO;
import com.mgbooking.server.DTOS.Hotel.ImageListDto;
import com.mgbooking.server.Entities.District;
import com.mgbooking.server.Services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping("UpdateMultipleImage/{id}")
    public ResponseEntity<Object>UpdateMultipleImage(@PathVariable("id")int id,@RequestParam(value = "MultiImage",required = false)List<MultipartFile> MultiImage) {
        try {
            Map<String,Object>response=new LinkedHashMap<>();
            if(hotelService.UpdateMultipleImages(id,MultiImage)){
                response.put("status",200);
                response.put("message","Update Multiple Image Success");
                return ResponseEntity.ok(response);
            }else{
                response.put("status",400);
                response.put("message","Update Multiple Image Failed");
                return ResponseEntity.badRequest().body(response);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @DeleteMapping(value = "DeletePictureImage/{id}")
    public ResponseEntity<Object>DeletePictureImage(@PathVariable("id") int id) {
        Map<String,Object>response=new LinkedHashMap<>();
        if(hotelService.DeleteHotel(id)){
            response.put("status",200);
            response.put("message","Deleted Image Hotel SuccessFully");
            return ResponseEntity.ok(response);
        }else{
            response.put("status",400);
            response.put("message","Deleted Image Hotel SuccessFully");
            return ResponseEntity.badRequest().body(response);
        }
    }
    @GetMapping(value = "FindImage/{id}")
    public ResponseEntity<List<ImageListDto>>FindImage(@PathVariable(value = "id") int id) {
        try {
            return new ResponseEntity<List<ImageListDto>>(hotelService.FindImages(id), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<ImageListDto>>(HttpStatus.BAD_REQUEST);
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
    @PostMapping(value = "UpdateHotel")
    public ResponseEntity<Object>UpdateHotel(@ModelAttribute HotelUpdateDTO hotelUpdateDTO,@RequestParam("imageForm") MultipartFile imageFile){
    Map<String,Object>response=new LinkedHashMap<>();
    if(hotelService.updateHotel(hotelUpdateDTO,imageFile)){
        response.put("status",200);
        response.put("message","Hotel added successfully");
        return  ResponseEntity.ok(response);
    }else{
        response.put("status",400);
        response.put("message","Hotel added failed");
        return ResponseEntity.badRequest().body(response);
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
