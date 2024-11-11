package com.mgbooking.server.Controllers;

import com.mgbooking.server.DTOs.RegisterAccountDto;
import com.mgbooking.server.Services.AccountServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"","/"})
@CrossOrigin(origins = "http://localhost:8386")
public class OwnerController {
    @Autowired
    private AccountServiceImplement accountService;
    @PostMapping(value = "registerOwner",produces = MimeTypeUtils.APPLICATION_JSON_VALUE,consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object>registerOwner(@RequestBody RegisterAccountDto registerAccountDto){
        try {
            return new ResponseEntity<>(new Object(){
                public boolean result= accountService.registerAccount(registerAccountDto);
            },HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
