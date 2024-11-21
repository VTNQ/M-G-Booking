package com.mgbooking.server.Controllers;

import com.mgbooking.server.Configurations.JwtUtil;
import com.mgbooking.server.DTOS.AccountDto;
import com.mgbooking.server.DTOS.AuthenticationResponse;
import com.mgbooking.server.DTOS.LoginDTO;
import com.mgbooking.server.Services.OwnerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private OwnerService accountService;

    @Autowired
    private JwtUtil jwtTokenUtil;

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
  @PostMapping("login")



  public ResponseEntity<Map<String,String>> createAuthenticationToken(@RequestBody LoginDTO authenticationRequest) throws Exception {
      try {
          authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
          );


      final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
      final String jwt = jwtTokenUtil.generateToken(userDetails);
      AuthenticationResponse authenticationResponse=new AuthenticationResponse(jwt);
          Map<String, String> response = new HashMap<>();
          response.put("AccessToken", jwt);

          // Return the response with the AccessToken key
          return ResponseEntity.ok(response);
      } catch (BadCredentialsException e) {
          throw new Exception("Invalid username or password", e);
      }
  }



    // Optional: API for logout, typically handled by Spring Security's session management
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Invalidate the session or clear the security context
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out successfully");
    }

    // Inner class for response containing JWT

}
