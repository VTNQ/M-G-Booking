package com.mgbooking.server.Controllers;

import com.mgbooking.server.DTOS.District.DistrictDTO;
import com.mgbooking.server.Entities.District;
import com.mgbooking.server.Services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("FindByDistrict/{id}")
    public ResponseEntity<DistrictDTO>FindByDistrict(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<DistrictDTO>(districtService.GetDistrictById(id), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<DistrictDTO>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("update")
    public ResponseEntity<Object>update(@RequestBody DistrictDTO dto) {
        try {
            Map<String,Object>response=new LinkedHashMap<>();
            if(districtService.AddDistrict(dto)){
                response.put("status",200);
                response.put("message","District updated successfully");
                return ResponseEntity.ok(response);
            }else{
                response.put("status",400);
                response.put("message","District updated failed");
                return ResponseEntity.badRequest().body(response);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("add")
    public ResponseEntity<Object> AddDistrict(@RequestBody DistrictDTO district) {
        try {
            Map<String,Object>response=new LinkedHashMap<>();
            if(districtService.AddDistrict(district)){
                response.put("status",200);
                response.put("message","Add District Success");
                return ResponseEntity.ok(response);
            }else{
                response.put("status",400);
                response.put("message","Add District Failed");
                return ResponseEntity.badRequest().body(response);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
}
