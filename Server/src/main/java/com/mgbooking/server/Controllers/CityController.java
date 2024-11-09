package com.mgbooking.server.Controllers;

import com.mgbooking.server.Entities.City;
import com.mgbooking.server.Services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping({"/City"})
@CrossOrigin(origins = "http://localhost:8386")
public class CityController
{
    @Autowired
    private CityService cityService;
    @GetMapping({"{id}"})
    public ResponseEntity<List<City>>GetCity(@PathVariable("id") int id){
        try {
            return new ResponseEntity<List<City>>(cityService.GetAllCities(id), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List<City>>(HttpStatus.BAD_REQUEST);
        }
    }
}
