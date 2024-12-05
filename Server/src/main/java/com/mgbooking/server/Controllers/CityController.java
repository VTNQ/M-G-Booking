package com.mgbooking.server.Controllers;

import com.mgbooking.server.DTOS.CityDTO;
import com.mgbooking.server.Entities.City;
import com.mgbooking.server.Services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping({"/City"})
@CrossOrigin(origins = "http://localhost:8386")
public class CityController
{
    @Autowired
    private CityService cityService;

    @DeleteMapping({"DeleteCity/{id}"})
    public ResponseEntity<Object>DeleteCity(@PathVariable("id")int id){
        try {
            return new ResponseEntity<>(new Object(){
                public boolean result=cityService.DeleteCity(id);
            },HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping({"FindCity/{id}"})
    public ResponseEntity<CityDTO>FindCity(@PathVariable("id")int id){
        try {
            return new ResponseEntity<CityDTO>(cityService.FindCity(id),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<CityDTO>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping({"{id}"})
    public ResponseEntity<List<City>>GetCity(@PathVariable("id") int id){
        try {
            return new ResponseEntity<List<City>>(cityService.GetAllCities(id), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List<City>>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "CreateCity",produces = MimeTypeUtils.APPLICATION_JSON_VALUE,consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>CreateCity(@RequestBody CityDTO city){
        try {
            return new ResponseEntity<>(new Object(){
                public boolean result=cityService.AddCity(city);
            },HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
