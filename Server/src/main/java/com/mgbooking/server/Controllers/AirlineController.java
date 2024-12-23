package com.mgbooking.server.Controllers;

import com.mgbooking.server.DTOS.AirLineDTO;

import com.mgbooking.server.DTOS.ListFlightDto;
import com.mgbooking.server.DTOS.UpdateFlightDTO;
import com.mgbooking.server.Entities.Airline;
import com.mgbooking.server.Services.AirlineService;
import com.mgbooking.server.Services.ValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Airline")
public class AirlineController {
    @Autowired
    private ValidationService validationService;
    @Autowired
    private AirlineService flightService;
    @GetMapping(value = "/SearchAirline")
    public ResponseEntity<List<Airline>>SearchAirline(@RequestParam("departureAirport")int departureAirport, @RequestParam("arrivalAirport") int arrivalAirport,
                                                @RequestParam("departureTime") LocalDate departureTime, @RequestParam("TypeFlight")String TypeFlight){
        try {
            return new ResponseEntity<List<Airline>>(flightService.SearchAirline(departureAirport,arrivalAirport,departureTime,TypeFlight),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List<Airline>>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "/UpdateFlight")
    public ResponseEntity<Object>UpdateFlight(@ModelAttribute UpdateFlightDTO flightDTO,@RequestParam("imageForm")MultipartFile imageFile){
        try {
            return new ResponseEntity<>(new Object(){
                public boolean result= flightService.UpdateFlight(flightDTO,imageFile);
            }, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/FindAll")
    public ResponseEntity<List<ListFlightDto>>FindAll(){
        try {
            return new ResponseEntity<List<ListFlightDto>>(flightService.FindAll(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List<ListFlightDto>>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "/FindFlight/{id}")
    public ResponseEntity<UpdateFlightDTO>FindFlight(@PathVariable int id){
        try {
            return new ResponseEntity<UpdateFlightDTO>(flightService.FindFlight(id),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<UpdateFlightDTO>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "FindAirline/{id}")
    public ResponseEntity<List<ListFlightDto>>FindAirLine(@PathVariable int id){
        try {
            return new ResponseEntity<List<ListFlightDto>>(flightService.ShowAirlineDto(id),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List<ListFlightDto>>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/AddFlight")
    public ResponseEntity<Object> AddFlight(@ModelAttribute @Valid AirLineDTO flightDTO, BindingResult bindingResult) {
        List<String>errors=validationService.validate(flightDTO, bindingResult);
        if (errors != null && !errors.isEmpty()) {
            // Trả về lỗi validation dưới dạng ApiResponse
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("status", 400);
            response.put("message", errors);


            return ResponseEntity.badRequest().body(response);
        }
        Map<String, Object> response = new LinkedHashMap<>();
        if(flightService.AddFlight(flightDTO)){
          response.put("status", 200);
          response.put("message", "Create Flight Successfully");
            return  ResponseEntity.ok(response);
        }else{
            response.put("status", 400);
            response.put("message", "Create Flight Failed");
            return ResponseEntity.badRequest().body(response);
        }

    }

}
