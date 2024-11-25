package com.mgbooking.server.Controllers;

import com.mgbooking.server.DTOS.FlightDTO;
import com.mgbooking.server.DTOS.FlightListDto;
import com.mgbooking.server.Services.FlightService;
import com.mgbooking.server.Services.ValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
