package com.mgbooking.client.Controllers.Admin;

import com.mgbooking.client.Configuration.GetToken;
import com.mgbooking.client.DTO.AccountDto;
import com.mgbooking.client.DTO.FlightDTO;
import com.mgbooking.client.Services.AirPortService;
import com.mgbooking.client.Services.AirlineService;
import com.mgbooking.client.Services.AuthService;
import com.mgbooking.client.Services.FlightService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller("AdminFlightController")
@RequestMapping({"/Admin"})
public class FlightController {
    @Autowired
    private AirlineService airlineService;
    @Autowired
    private GetToken tokenService;
    @Autowired
    private AirPortService airportService;
    @Autowired
    private FlightService flightService;
    @Autowired
    private AuthService authService;
    @PostMapping("Flight")
    public String Flight(@ModelAttribute("flight")FlightDTO flightDTO, HttpServletRequest request, RedirectAttributes redirectAttributes){
        String token=tokenService.getTokenFromCookies(request);
        Object createObject=flightService.CreateFlight(token,flightDTO);
        Map<String, Object> resultMap = (Map<String, Object>) createObject;
        Object statusObj = resultMap.get("status");
        int status = 0;
        if (statusObj instanceof Double) {
            status = ((Double) statusObj).intValue();
        } else if (statusObj instanceof Integer) {
            status = (Integer) statusObj;
        }
        String message=resultMap.get("message").toString();
        if (status == 200) {

            redirectAttributes.addFlashAttribute("message",message);
            redirectAttributes.addFlashAttribute("messageType","success");
            return "redirect:/Admin/Flight";
        } else {
            redirectAttributes.addFlashAttribute("message",message);
            redirectAttributes.addFlashAttribute("messageType","error");
            return "redirect:/Admin/Flight";
        }
    }
 @GetMapping("Flight")
    public String Flight(ModelMap model, HttpServletRequest httpServletRequest) {
     String token=tokenService.getTokenFromCookies(httpServletRequest);
     AccountDto accountDto=authService.FindByAccount(token);
     model.put("flight",new FlightDTO());
     model.put("Airline",airlineService.FindAirlineByCountry(token,accountDto.getCountryId()));
     model.put("AirPort",airportService.FindAllByCountry(token,accountDto.getCountryId()));
     return "Admin/Flight/Flight";
 }
}
