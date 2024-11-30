package com.mgbooking.server.Controllers;

import com.mgbooking.server.DTOS.*;
import com.mgbooking.server.Entities.Flight;
import com.mgbooking.server.Services.FlightService;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Flight")
public class FlightController {
    @Autowired
    private ValidationService validationService;
    @Autowired
    private FlightService flightService;
    @GetMapping(value = "FindAll/{id}")
    public ResponseEntity<Page<FlightPaginateDTo>> findAllFlights(@PathVariable int id, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size) {
        try {
            Pageable pageable= PageRequest.of(page,size);
            return new ResponseEntity<Page<FlightPaginateDTo>>(flightService.FindAllByCountry(pageable,id),HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Page<FlightPaginateDTo>>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("SearchFlight")
    public ResponseEntity<List<ResultFlightDTO>>SearchFlight(@RequestParam("departureAirport")int departureAirport, @RequestParam("arrivalAirport") int arrivalAirport,
                                                             @RequestParam("departureTime")LocalDate departureTime,@RequestParam("TypeFlight")String TypeFlight) {
        try {
            return new ResponseEntity<List<ResultFlightDTO>>(flightService.SearchFlight(departureAirport,arrivalAirport,departureTime,TypeFlight),HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<ResultFlightDTO>>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping(value = "FindPrice")
    public ResponseEntity<BigDecimal>FindPrice(@RequestParam("departureTime")LocalDate departureTime){
        try {
            return new ResponseEntity<BigDecimal>(flightService.FindPrice(departureTime),HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<BigDecimal>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(value = "UpdateFlight")
    public ResponseEntity<Object>UpdateFlight(@RequestBody @Valid FlightListDto flightDTO, BindingResult bindingResult) {
        try {
            List<String>errors=validationService.validate(flightDTO,bindingResult);
            if(errors!=null && !errors.isEmpty()){
                Map<String,Object> response=new LinkedHashMap<>();
                response.put("status",400);
                response.put("message",errors);
                return ResponseEntity.badRequest().body(response);
            }
            Map<String, Object> response = new LinkedHashMap<>();
            if(flightService.UpdateInformationFlight(flightDTO)){
                response.put("status",200);
                response.put("message","Update Flight Successful");
                return ResponseEntity.ok(response);
            }else{
                response.put("status",400);
                response.put("message","Update Flight Failed");
                return ResponseEntity.badRequest().body(response);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "/CreateFlight")
    public ResponseEntity<Object> CreateFlight(@RequestBody @Valid FlightDTO flightDTO, BindingResult bindingResult) {
        try {
            List<String> errors=validationService.validate(flightDTO,bindingResult);
            if (errors != null && !errors.isEmpty()) {
                // Trả về lỗi validation dưới dạng ApiResponse
                Map<String, Object> response = new LinkedHashMap<>();
                response.put("status", 400);
                response.put("message", errors);


                return ResponseEntity.badRequest().body(response);
            }
            Map<String, Object> response = new LinkedHashMap<>();
            if(flightService.CreateFlight(flightDTO)){
                response.put("status", 200);
                response.put("message", "Create Flight Successful");
                return ResponseEntity.ok(response);
            }else{
                response.put("status", 400);
                response.put("message", "Create Flight Failed");
                return ResponseEntity.badRequest().body(response);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "FindFlight/{id}")
    public ResponseEntity<FlightListDto>FindFlight(@PathVariable int id){
        try {
            return new ResponseEntity<FlightListDto>(flightService.GetFlight(id),HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<FlightListDto>(HttpStatus.BAD_REQUEST);
        }
    }
}
