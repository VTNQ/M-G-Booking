package com.mgbooking.server.Controllers;

import com.mgbooking.server.DTOS.RegisterUser;
import com.mgbooking.server.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping({"/",""})
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping(value = "registerUser",produces = MimeTypeUtils.APPLICATION_JSON_VALUE,consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerUser(@RequestBody RegisterUser registerUser){
        try {
            return new ResponseEntity<>(new Object(){
                public boolean result=userService.CreateUser(registerUser);
            },HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
