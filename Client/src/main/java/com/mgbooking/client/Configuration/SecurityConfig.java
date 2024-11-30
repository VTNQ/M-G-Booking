package com.mgbooking.client.Configuration;

import com.mysql.cj.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private TokenFilter tokenFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(cs->cs.disable())// Disable CSRF protection
                .authorizeRequests()
                .requestMatchers("/Login","/Home","/css/**","/js/**","/user/**","/SuperAdmin/assets/**","/images/**","/LoginAdmin","/Profile","/Flight","/Hotel","/RegisterUser","/RegisterOwner","/SearchFlight").permitAll()
                .requestMatchers("/SuperAdmin/Home","/SuperAdmin/Country","/SuperAdmin/EditCountry/**","/SuperAdmin/UpdateCountry","/SuperAdmin/DeleteCountry/**","/SuperAdmin/Airline","/SuperAdmin/UpdateAirline/**").hasAnyRole("SUPERADMIN")
                .requestMatchers("/UpdateProfile").hasAnyRole("USER")
                .requestMatchers("/Owner","/Owner/Profile","/Owner/UpdateProfile").hasRole("OWNER")
                .requestMatchers("/Admin/Home","/Admin/City","/Admin/City/{id}","/Admin/UpdateCity","/Admin/DeleteCity/**","/Admin/AirPort","/Admin/AirPort/Edit/{id}","/Admin/AirPort/UpdateAirPort","/Admin/Flight","/Admin/Flight/Edit/{id}","/Admin/Flight/UpdateFlight","/Admin/Flight/UpdateDetailFlight").hasRole("ADMIN")// Allow anyone to access /Home
                .anyRequest().authenticated() // Require authentication for other requests
                .and()

               // Custom login page
                 // URL to redirect to on login failure
                 // Allow everyone to access the login page

                .exceptionHandling(ex->ex.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/Login"))).addFilterBefore(tokenFilter,UsernamePasswordAuthenticationFilter.class); // Stateless session

        return http.build();
    }
}
