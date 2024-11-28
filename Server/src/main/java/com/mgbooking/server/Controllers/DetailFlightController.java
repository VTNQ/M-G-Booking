package com.mgbooking.server.Controllers;

import com.mgbooking.server.DTOS.DetailFlightDTO;
import com.mgbooking.server.Entities.DetailFlight;
import com.mgbooking.server.Services.DetailFlightService;
import com.mgbooking.server.Services.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping({"/DetailFlight"})
public class DetailFlightController {
    @Autowired
    private DetailFlightService detailFlightService;
    @Autowired
    private ValidationService validationService;
    @PutMapping("UpdateDetail")
    public ResponseEntity<Object>UpdateDetail(@RequestBody List<DetailFlightDTO> detailFlightDTO){
        Map<String, Object> response = new LinkedHashMap<>();
        if(detailFlightService.UpdateDetailFlight(detailFlightDTO)){
            response.put("status", 200);
            response.put("message", "Update DetailFlight Successfully");
            return  ResponseEntity.ok(response);
        }else{
            response.put("status", 400);
            response.put("message", "Update DetailFlight Failed");
            return ResponseEntity.badRequest().body(response);
        }
    }
    @GetMapping("{id}")
    public ResponseEntity<List<DetailFlightDTO>> getDetailFlight(@PathVariable int id) {
        try {
            return new ResponseEntity<List<DetailFlightDTO>>(detailFlightService.FindAll(id),HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<DetailFlightDTO>>(HttpStatus.BAD_REQUEST);
        }
    }
}
