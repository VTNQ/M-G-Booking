package com.mgbooking.server.Controllers;

import com.mgbooking.server.DTOS.AirPortDTO;
import com.mgbooking.server.DTOS.AirPortList;
import com.mgbooking.server.DTOS.CountryAiportDTO;
import com.mgbooking.server.DTOS.SearchAiportDTO;
import com.mgbooking.server.Entities.Airport;
import com.mgbooking.server.Services.AirPortService;
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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/AirPort")
public class AirPortController {
    @Autowired
    private AirPortService airPortService;
    @Autowired
    private ValidationService validationService;
    @GetMapping("/FindAllByCountry/{id}")
    public ResponseEntity<List<Airport>>FindAllByCountry(@PathVariable int id){
        try {
            return new ResponseEntity<List<Airport>>(airPortService.FindAirPortByCountry(id),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/FindById/{id}")
    public ResponseEntity<AirPortDTO>FindById(@PathVariable int id) {
        try {
        return new ResponseEntity<AirPortDTO>(airPortService.FindById(id),HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<AirPortDTO>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/EditAirPort")
    public ResponseEntity<Object>EditAirPort(@RequestBody @Valid AirPortDTO airPortDTO, BindingResult bindingResult){
        try {
            List<String>errors=validationService.validate(airPortDTO,bindingResult);
            if (errors != null && !errors.isEmpty()) {
                // Trả về lỗi validation dưới dạng ApiResponse
                Map<String, Object> response = new LinkedHashMap<>();
                response.put("status", 400);
                response.put("message", errors);


                return ResponseEntity.badRequest().body(response);
            }
            Map<String, Object> response = new LinkedHashMap<>();
            if(airPortService.AddAirPort(airPortDTO)){
                response.put("status", 200);
                response.put("message", "Update AirPort Successfully");
                return  ResponseEntity.ok(response);
            }else{
                response.put("status", 400);
                response.put("message", "Update AirPort Failed");
                return ResponseEntity.badRequest().body(response);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping(value = "/SearchAirPort")
    public List<CountryAiportDTO> SearchAirPort(@RequestParam String search){
        return airPortService.SearchAirPort(search);
    }
    @PostMapping(value = "/CreateAirPort")
    public ResponseEntity<Object>CreateAirPort(@RequestBody @Valid AirPortDTO airPort, BindingResult bindingResult) {
        List<String>errors=validationService.validate(airPort,bindingResult);
        if (errors != null && !errors.isEmpty()) {
            // Trả về lỗi validation dưới dạng ApiResponse
            Map<String, Object> response = new LinkedHashMap<>();
            response.put("status", 400);
            response.put("message", errors);


            return ResponseEntity.badRequest().body(response);
        }
        Map<String, Object> response = new LinkedHashMap<>();
        if(airPortService.AddAirPort(airPort)){
            response.put("status", 200);
            response.put("message", "Create AirPort Successfully");
            return  ResponseEntity.ok(response);
        }else{
            response.put("status", 400);
            response.put("message", "Create AirPort Failed");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
