package com.mgbooking.server.Controller;

import com.mgbooking.server.DTOS.RegisterOwnerDto;
import com.mgbooking.server.Services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"","/"})
public class OwnerController {
    @Autowired
    private OwnerService ownerService;
    @PostMapping(value = "registerOwner",produces = MimeTypeUtils.APPLICATION_JSON_VALUE,consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
public ResponseEntity<Object>registerOwner(@RequestBody RegisterOwnerDto registerOwnerDto){
        try {
            return new ResponseEntity<>(new Object(){
                public boolean result=ownerService.RegisterOwner(registerOwnerDto);
            },HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
