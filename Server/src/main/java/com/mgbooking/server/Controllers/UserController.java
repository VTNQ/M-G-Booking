package com.mgbooking.server.Controllers;

import com.mgbooking.server.DTOS.Account.AccountAdmin;
import com.mgbooking.server.DTOS.Account.RegisterAdmin;
import com.mgbooking.server.DTOS.ForgotPassword.ChangePassword;
import com.mgbooking.server.DTOS.ForgotPassword.CheckOTP;
import com.mgbooking.server.DTOS.ForgotPassword.ForgetDTO;
import com.mgbooking.server.DTOS.RegisterUser;
import com.mgbooking.server.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping({"/",""})
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("GetAccountAdmin")
    public ResponseEntity<List<AccountAdmin>> getAccountAdmin() {
        try {
        return new ResponseEntity<List<AccountAdmin>>(userService.getAccountAdmin(),HttpStatus.OK);
        }catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<List<AccountAdmin>>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("CreateAdminAccount")
    public ResponseEntity<Object>CreateAdminAccount(@RequestBody RegisterAdmin registerAdmin){
        try {
            Map<String,Object>response=new LinkedHashMap<>();
            if(userService.RegisterAdmin(registerAdmin)){
                response.put("status",200);
                response.put("message","Create Account Success");
                return ResponseEntity.ok(response);
            }else{
                response.put("status",400);
                response.put("message","Create Account Failed");
                return ResponseEntity.badRequest().body(response);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("ResendOTP")
    public ResponseEntity<Object>ResendOTP(@RequestBody ForgetDTO forgetDTO){
    try {
        Map<String,Object>response=new LinkedHashMap<>();
        if(userService.ResendOTP(forgetDTO.getEmail())){
            response.put("status",200);
            response.put("message","Resend OTP successful");
            return ResponseEntity.ok(response);
        }else{
            response.put("status",400);
            response.put("message","Resend OTP failed");
            return ResponseEntity.badRequest().body(response);
        }
    }catch (Exception e){
        e.printStackTrace();
        return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
    }
    }
    @PostMapping("ChangePassword")
    public ResponseEntity<Object>ChangePassword(@RequestBody ChangePassword changePassword){
        try{
        Map<String,Object>response=new LinkedHashMap<>();
        if(userService.ChangePassword(changePassword.getEmail(),changePassword.getPassword())){
            response.put("status",200);
            response.put("message","Password changed successfully");
            return ResponseEntity.ok(response);
        }else{
            response.put("status",400);
            response.put("message","Change password failed");
            return ResponseEntity.badRequest().body(response);
        }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "CheckOTP")
    public ResponseEntity<Object>CheckOTP(@RequestBody CheckOTP checkOTP){
        try {
        Map<String,Object>response=new LinkedHashMap<>();
        if(userService.CheckOTP(checkOTP.getEmail(),checkOTP.getOtp())){
            response.put("status",200);
            response.put("message","OTP is Correct");
            return ResponseEntity.ok(response);
        }else{
            response.put("status", 400);
            response.put("message", "OTP is incorrect or has expired");
            return ResponseEntity.badRequest().body(response);
        }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "ForgetPassword")
    public ResponseEntity<Object>ForgetPassword(@RequestBody ForgetDTO forgetDTO){
        try {
            Map<String,Object>response=new LinkedHashMap<>();
            if(userService.ForgetAccount(forgetDTO.getEmail())){
                response.put("status",200);
                response.put("message","Forget Password Success");
                return ResponseEntity.ok(response);
            }else{
                response.put("status",400);
                response.put("message","Forget Password Failed");
                return ResponseEntity.badRequest().body(response);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(value = "registerUser",produces = MimeTypeUtils.APPLICATION_JSON_VALUE,consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerUser(@RequestBody RegisterUser registerUser){
        try {
            return new ResponseEntity<>(new Object(){
                public boolean result=userService.RegisterUser(registerUser);
            },HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
