package com.mgbooking.server.Controllers;

import com.mgbooking.server.Entities.Country;
import com.mgbooking.server.Services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController()
@RequestMapping({"/Country"})
@CrossOrigin(origins = "http://localhost:8386")
public class CountryController {
    @Autowired
    private CountryService CountryService;
    @GetMapping( {"","/"})
    public ResponseEntity<List<Country>> GetAllCountries(){
        try {

                return new ResponseEntity<List<Country>>(CountryService.findAll(),HttpStatus.OK);


        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List<Country>>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("GetAllCountries")
    @CrossOrigin(origins = "http://localhost:8386")
    public ResponseEntity<Page<Country>>GetAllCountries(@RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "10")int size){
        try {
            Pageable pageable= PageRequest.of(page,size);
                return new ResponseEntity<Page<Country>>(CountryService.findByPage(pageable),HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<Page<Country>>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "CreateCountry",produces = MimeTypeUtils.APPLICATION_JSON_VALUE,consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>CreateCountry(@RequestBody Country Country){
        try {
            return new ResponseEntity<>(new Object(){
                public boolean result=CountryService.CreateCountry(Country);
            },HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
