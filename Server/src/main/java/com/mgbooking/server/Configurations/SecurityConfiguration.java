package com.mgbooking.server.Configurations;

import com.mgbooking.server.Services.AccountServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private JWTTokenFilter jwtTokenFilter;

    @Autowired
    private AccountServiceDetail customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Lazy
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        // Set custom UserDetailsService and PasswordEncoder
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());

        return authenticationManagerBuilder.build(); // Build the AuthenticationManager
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(cs -> cs.disable())
                .cors(cs->cs.configurationSource(corsConfigurationSource()))
                .authorizeRequests()
                .requestMatchers("/api/auth/login").permitAll()
                .requestMatchers("/Country", "/Country/CreateCountry","Country/UpdateCountry","Country/DeleteCountry/**", "/Country/GetAllCountries/**").hasRole("SUPERADMIN")
                .requestMatchers("City/CreateCity").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    // Define CORS configuration in WebMvcConfigurer
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8386")); // Replace with your frontend origin
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));

        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
