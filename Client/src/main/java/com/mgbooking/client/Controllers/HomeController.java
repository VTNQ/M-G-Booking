package com.mgbooking.client.Controllers;

import com.mgbooking.client.Configuration.JwtUtil;
import com.mgbooking.client.DTO.ResultFlightDTO;
import com.mgbooking.client.DTO.SearchFlightDTO;
import com.mgbooking.client.Services.AirlineService;
import com.mgbooking.client.Services.FlightService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller()
@RequestMapping({"","/"})
public class HomeController {
    @Autowired
    private FlightService flightService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AirlineService airlineService;
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
        String selectedDateStr=searchFlightDTO.getDepartureTime();
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate selectedDate = LocalDate.parse(selectedDateStr, formatter);
        List<Map<String, String>> dateList = new ArrayList<>();

        for (int i = 0; i <= 4; i++) {
            LocalDate nextDate = selectedDate.plusDays(i);
            String nextDateStr = nextDate.format(formatter);
            BigDecimal minPrice = flightService.FindMinPrice(nextDateStr);

            Map<String, String> dateMap = new HashMap<>();
            dateMap.put("day", String.valueOf(nextDate.getDayOfMonth()));
            dateMap.put("month", String.valueOf(nextDate.getMonthValue()));
            dateMap.put("minprice",String.valueOf(minPrice));
            modelMap.put("Flight"+i,flightService.SearchFlights(searchFlightDTO.getDepartureAirport(),searchFlightDTO.getArrivalAirport(),nextDateStr,searchFlightDTO.getTypeFlight()));
            dateList.add(dateMap);
        }
        modelMap.put("dateList", dateList);

        modelMap.put("Airline",airlineService.SearchAirline(searchFlightDTO));
    modelMap.put("url",url);
    return "User/Flight/Flight";
    }
}
