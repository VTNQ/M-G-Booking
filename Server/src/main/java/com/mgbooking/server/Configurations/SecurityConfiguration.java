package com.mgbooking.server.Configurations;

import com.mgbooking.server.Services.AccountServiceDetail;
import jakarta.servlet.http.HttpServletResponse;
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
import org.springframework.security.web.access.AccessDeniedHandler;
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
                .requestMatchers("/auth/login","/auth/logout","/images/**", "/favicon.ico", "/css/**", "/js/**","/Country","/City/{id}","/registerUser","/registerOwner","/Flight/SearchFlight","/AirPort/SearchAirPort","/Flight/ShowDetailFlight/{id}","/Flight/FindPrice").permitAll()

                .requestMatchers(
                        "/Country/CreateCountry",
                        "Country/UpdateCountry",
                        "Country/DeleteCountry/**",
                        "/Country/GetAllCountries/**",
                        "/Country/FindByCountry/**","/Airline/AddFlight","/Airline/GetFlight","/Airline/UpdateFlight","/Airline/FindFlight/{id}").hasRole("SUPERADMIN")
                .requestMatchers("City/CreateCity","/City/{id}","/City/FindCityPage/**","/City/FindCity/**","/City/DeleteCity/**","/AirPort/CreateAirPort","/AirPort/{id}","/AirPort/FindById/{id}","/AirPort/EditAirPort","/Flight/CreateFlight","/Airline/FindAirline/{id}","/AirPort/FindAllByCountry/{id}","/Flight/FindFlight/{id}","/Flight/FindAll/**",
                        "/Flight/UpdateFlight","/DetailFlight/{id}","/DetailFlight/UpdateDetail").hasRole("ADMIN")

                .requestMatchers("/auth/","/auth/UpdateAccount").hasAnyRole("ADMIN", "SUPERADMIN","USER","OWNER")
                .anyRequest().authenticated()
                .and()
               .exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler()))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            String previousUrl = (String) request.getSession().getAttribute("previousUrl");
            if (previousUrl != null) {
                response.sendRedirect(previousUrl);
            } else {

                response.sendRedirect("/default-page");
            }
        };
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
