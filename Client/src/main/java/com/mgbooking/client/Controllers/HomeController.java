package com.mgbooking.client.Controllers;

import com.mgbooking.client.Configuration.JwtUtil;
import com.mgbooking.client.DTO.SearchFlightDTO;
import com.mgbooking.client.Services.FlightService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping({"","/"})
public class HomeController {
    @Autowired
    private FlightService flightService;
    @Autowired
    private JwtUtil jwtUtil;
    @Value("${FlightUrl}")
    private String url;
    @GetMapping("Home")
    public String Home(HttpServletRequest Request, ModelMap modelMap) {
        String token=(String) Request.getSession().getAttribute("accessToken");
        modelMap.put("Search",new SearchFlightDTO());

        return "User/Home/Home";
    }
    @GetMapping("SearchFlight")
    public String SearchFlight(HttpServletRequest Request, ModelMap modelMap, @ModelAttribute("Search")SearchFlightDTO searchFlightDTO) {

        modelMap.put("Flight",flightService.SearchFlights(searchFlightDTO));
    modelMap.put("url",url);
    return "User/Flight/Flight";
    }
}
