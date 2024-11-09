package com.mgbooking.server.Controller;

import com.mgbooking.server.Entities.Country;
import com.mgbooking.server.Services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping({"/Country"})
public class CountryController {
    @Autowired
    private CountryService countryService;
    @GetMapping( {"","/"})
    public ResponseEntity<List<Country>> GetAllCountries(){
        try {
        return new ResponseEntity<List<Country>>(countryService.findAll(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List<Country>>(HttpStatus.BAD_REQUEST);
        }
    }
}
