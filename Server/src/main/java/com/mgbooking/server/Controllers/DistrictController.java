package com.mgbooking.server.Controllers;

import com.mgbooking.server.Entities.District;
import com.mgbooking.server.Services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/District")
public class DistrictController {
    @Autowired
    private DistrictService districtService;
    @GetMapping("GetDistrict/{id}")
    public ResponseEntity<List<District>> GetDistrict(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<List<District>>(districtService.GetDistricts(id), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<District>>(HttpStatus.BAD_REQUEST);
        }
    }
}
