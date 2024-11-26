package com.mgbooking.client.Controllers.Admin;

import com.mgbooking.client.Configuration.GetToken;
import com.mgbooking.client.DTO.AccountDto;
import com.mgbooking.client.DTO.FlightDTO;
import com.mgbooking.client.DTO.FlightListDto;
import com.mgbooking.client.Services.AirPortService;
import com.mgbooking.client.Services.AirlineService;
import com.mgbooking.client.Services.AuthService;
import com.mgbooking.client.Services.FlightService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("Flight/Edit/{id}")
    public String Edit(@PathVariable("id") int id,ModelMap model, HttpServletRequest request){
        String token=tokenService.getTokenFromCookies(request);
        model.put("flight",flightService.GetFlights(token,id));
        AccountDto accountDto=authService.FindByAccount(token);
        model.put("AirPort",airportService.FindAllByCountry(token,accountDto.getCountryId()));
        model.put("Airline",airlineService.FindAirlineByCountry(token,accountDto.getCountryId()));
        return "Admin/Flight/Edit";
    }
    @PostMapping("Flight/UpdateFlight")
    public String Update(@ModelAttribute("flight")FlightListDto flightListDto,HttpServletRequest request,RedirectAttributes redirectAttributes){
        String token=tokenService.getTokenFromCookies(request);
        Object createObject=flightService.UpdateFlight(token,flightListDto);
        Map<String, Object> resultMap = (Map<String, Object>) createObject;
        Object statusObj = resultMap.get("status");
        int status = 0;
        if (statusObj instanceof Double) {
            status = ((Double) statusObj).intValue();
        } else if (statusObj instanceof Integer) {
            status = (Integer) statusObj;
        }
        String message=resultMap.get("message").toString();
        if(status==200){
            redirectAttributes.addFlashAttribute("message",message);
            redirectAttributes.addFlashAttribute("messageType","success");
            return "redirect:/Admin/Flight/Edit/"+flightListDto.getId();
        }else{
            redirectAttributes.addFlashAttribute("message",message);
            redirectAttributes.addFlashAttribute("messageType","error");
            return "redirect:/Admin/Flight/Edit/"+flightListDto.getId();
        }
    }
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
     model.put("IdCountry",accountDto.getCountryId());
     model.put("token",token);
     model.put("Airline",airlineService.FindAirlineByCountry(token,accountDto.getCountryId()));
     model.put("AirPort",airportService.FindAllByCountry(token,accountDto.getCountryId()));
     return "Admin/Flight/Flight";
 }
}
