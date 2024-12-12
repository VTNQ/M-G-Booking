package com.mgbooking.server.Controllers;

import com.mgbooking.server.DTOS.Service.ServiceDTO;
import com.mgbooking.server.Entities.Service;
import com.mgbooking.server.Services.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/Service"})
public class ServiceController {
    @Autowired
    private ServiceInterface serviceInterface;
    @GetMapping("FindAll/{id}")
    public ResponseEntity<List<Service>> FindAll(@PathVariable int id) {
        try {
            return new ResponseEntity<List<Service>>(serviceInterface.FindAll(id), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<Service>>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("findById/{id}")
    public ResponseEntity<ServiceDTO>FindById(@PathVariable int id) {
        try {
            return new ResponseEntity<ServiceDTO>(serviceInterface.FindById(id), HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ServiceDTO>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object>delete(@PathVariable int id) {
        try {
            Map<String,Object>response=new LinkedHashMap<>();
            if(serviceInterface.DeleteService(id)){
                response.put("status",200);
                response.put("message","Deleted Successfully");
                return ResponseEntity.ok(response);
            }else{
                response.put("status",400);
                response.put("message","Delete Service Failed");
                return ResponseEntity.badRequest().body(response);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("update")
    public ResponseEntity<Object>update(@RequestBody ServiceDTO serviceDTO) {
        try {
            Map<String,Object>response=new LinkedHashMap<>();
            if(serviceInterface.addService(serviceDTO)){
                response.put("status",200);
                response.put("message","Update Service Successfully");
                return ResponseEntity.ok(response);
            }else{
                response.put("status",400);
                response.put("message","Update Service Failed");
                return ResponseEntity.badRequest().body(response);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("add")
    public ResponseEntity<Object>add(@RequestBody ServiceDTO service) {
        try {
            Map<String,Object>response=new LinkedHashMap<>();
            if(serviceInterface.addService(service)){
                response.put("status",200);
                response.put("message","Service added successfully");
                return ResponseEntity.ok(response);
            }else{
                response.put("status",400);
                response.put("message","Service added failed");
                return ResponseEntity.badRequest().body(response);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
}
