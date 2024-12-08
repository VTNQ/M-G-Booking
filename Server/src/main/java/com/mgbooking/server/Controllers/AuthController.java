package com.mgbooking.server.Controllers;

import com.mgbooking.server.Configurations.JwtUtil;
import com.mgbooking.server.DTOS.AccountDto;
import com.mgbooking.server.DTOS.AuthenticationResponse;
import com.mgbooking.server.DTOS.LoginDTO;
import com.mgbooking.server.Services.OwnerService;

import com.mgbooking.server.Services.TokenBlackListService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private OwnerService accountService;
    @Autowired
    private TokenBlackListService tokenBlacklistService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserDetailsService userDetailsService;
    private final String jwtSecretKey="ThisIsSecretSecurity";
    @GetMapping({"","/"})
    public ResponseEntity<AccountDto>GetAccount(@RequestHeader(value =  "Authorization",required = true)String authorizationHeader){

        try {
            return new ResponseEntity<AccountDto>(accountService.GetAccount(authorizationHeader), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<AccountDto>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("UpdateAccount")
    public ResponseEntity<Object>UpdateAccount(@RequestBody AccountDto accountDto){
        Map<String, Object> response = new LinkedHashMap<>();
        if(accountService.updateAccount(accountDto)){
            response.put("status", 200);
            response.put("message", "Update Account Successfully");
            return  ResponseEntity.ok(response);
        }else{
            response.put("status", 400);
            response.put("message", "Update Account Failed");
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PostMapping("login")
    public ResponseEntity<Map<String, String>> createAuthenticationToken(@RequestBody LoginDTO authenticationRequest, HttpServletRequest request,HttpServletResponse response) throws Exception {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );

            // Load user details and generate tokens
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
            final String jwt = jwtTokenUtil.generateToken(userDetails);

            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("AccessToken", jwt);


            return ResponseEntity.ok(responseBody);
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid username or password", e);
        }
    }




    // Optional: API for logout, typically handled by Spring Security's session management
    @PostMapping("/logout")
    public ResponseEntity<Object>  logout(  @RequestHeader(value = "Authorization", required = false) String token,
                                            HttpServletRequest request,
                                            HttpServletResponse response) {
        Map<String, Object> responsebody = new LinkedHashMap<>();
            if(token==null || token.isEmpty()){
                        responsebody.put("status", 400);
                        responsebody.put("message", "Authorization token is missing");
                        return ResponseEntity.badRequest().body(responsebody);
            }
            if(tokenBlacklistService.isBlackListed(token)){
                responsebody.put("status", 400);
                responsebody.put("message", "Token has already been invalidated.");
                return ResponseEntity.badRequest().body(responsebody);
            }
        tokenBlacklistService.blackListTokens(token);
            responsebody.put("status", 200);
            responsebody.put("message", "Logout Successful");
            return ResponseEntity.ok(responsebody);

    }

    // Inner class for response containing JWT

}
