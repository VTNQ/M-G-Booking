package com.mgbooking.server.Controllers;

import com.mgbooking.server.Configurations.JwtUtil;
import com.mgbooking.server.DTOS.AccountDto;
import com.mgbooking.server.DTOS.AuthenticationResponse;
import com.mgbooking.server.DTOS.LoginDTO;
import com.mgbooking.server.Services.OwnerService;

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
    public List<String> getTokensFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<String> tokensList = (List<String>) session.getAttribute("tokensList");

        if (tokensList != null) {
            return tokensList; // Return the list of tokens if available
        }
        return Collections.emptyList(); // Return an empty list if no tokens are found
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
    public ResponseEntity<Map<String, String>> createAuthenticationToken(@RequestBody LoginDTO authenticationRequest, HttpServletRequest request) throws Exception {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );

            // Load user details and generate tokens
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
            final String jwt = jwtTokenUtil.generateToken(userDetails);

            // Retrieve tokens from session (get both access token and refresh token)
            List<String> tokensList = getTokensFromSession(request);
            if (tokensList.size()<=0) {
                tokensList = new ArrayList<>();
            }

            // Add the generated jwt to the tokens list
            tokensList.add(jwt);  // Adding the current access token (JWT)

            // Store the updated list of tokens back in session
            HttpSession session = request.getSession();
            session.setAttribute("tokensList", tokensList);

            // Prepare the response with the AccessToken and RefreshToken
            Map<String, String> response = new HashMap<>();
            response.put("AccessToken", jwt);


            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid username or password", e);
        }
    }




    // Optional: API for logout, typically handled by Spring Security's session management
    @PostMapping("/logout")
    public ResponseEntity<Object>  logout(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization");
        HttpSession session = request.getSession();
        Map<String, Object> responseMap = new LinkedHashMap<>();
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);


            List<String> tokensList = (List<String>) session.getAttribute("tokensList");

            if (tokensList != null && tokensList.contains(token)) {

                tokensList.remove(token);


                session.setAttribute("tokensList", tokensList);
                responseMap.put("status",200);
                responseMap.put("message","Update Flight Successful");
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("status",400);
                responseMap.put("message","Token not found in the session.");
                return ResponseEntity.badRequest().body(responseMap);
            }
        } else {
            responseMap.put("status",400);
            responseMap.put("message","Token not found in the session.");
            return ResponseEntity.badRequest().body("No Bearer token found in the Authorization header.");
        }
    }

    // Inner class for response containing JWT

}
