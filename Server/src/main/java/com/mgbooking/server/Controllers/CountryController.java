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
    @GetMapping("FindByCountry/{id}")
    public ResponseEntity<Country>FindByCountryId(@PathVariable int id){
        try {
            return new ResponseEntity<Country>(CountryService.findById(id),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Country>(HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping(value = "DeleteCountry/{id}",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>DeleteCountry(@PathVariable("id") int id){
    try {
        return new ResponseEntity<Object>(new Object() {
            public boolean result = CountryService.DeleteCountry(id);
        }, HttpStatus.OK);
    }catch (Exception e){
        e.printStackTrace();
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }
    }
    @PutMapping(value = "UpdateCountry",produces = MimeTypeUtils.APPLICATION_JSON_VALUE,consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>UpdateCountry(@RequestBody Country Country){
        try {
            return new ResponseEntity<>(new Object(){
                public boolean result=CountryService.CreateCountry(Country);
            },HttpStatus.OK);
        }catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
