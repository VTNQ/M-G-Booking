package com.mgbooking.client.Controllers.Admin;

import com.mgbooking.client.Configuration.GetToken;
import com.mgbooking.client.DTO.*;
import com.mgbooking.client.Services.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    private DetailFlightService detailFlightService;
    @Value("${url}")
    private String url;
    @GetMapping("Flight/Edit/{id}")
    public String Edit(@PathVariable("id") int id,ModelMap model, HttpServletRequest request){
        String token=tokenService.getTokenFromCookies(request);
        model.put("flight",flightService.GetFlights(token,id));
        model.put("detailFlight",detailFlightService.findDetail(token,id));
        model.put("url",url);
        model.put("token",token);
        AccountDto accountDto=authService.FindByAccount(token);
        model.put("AirPort",airportService.FindAllByCountry(token,accountDto.getCountryId()));
        model.put("Airline",airlineService.FindAirlineByCountry(token,accountDto.getCountryId()));
        return "Admin/Flight/Edit";
    }
    @GetMapping("Flight/add")
    public String addFlight(ModelMap model, HttpServletRequest request){
        String token=tokenService.getTokenFromCookies(request);
        AccountDto accountDto=authService.FindByAccount(token);
        model.put("flight",new FlightDTO());
        model.put("IdCountry",accountDto.getCountryId());
        model.put("token",token);
        model.put("Airline",airlineService.FindAirlineByCountry(token,accountDto.getCountryId()));
        model.put("AirPort",airportService.FindAllByCountry(token,accountDto.getCountryId()));
        return "Admin/Flight/add";
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
    @PostMapping("Flight/add")
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
    public String Flight(ModelMap model, HttpServletRequest httpServletRequest,@RequestParam(defaultValue = "1") int page,
                         @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "")String name) {
     String token=tokenService.getTokenFromCookies(httpServletRequest);
     AccountDto accountDto=authService.FindByAccount(token);

     model.put("token",token);
     List<FlightPaginateDTo>flight=flightService.ShowAll(token,accountDto.getCountryId());
     List<FlightPaginateDTo>flightPaginateDTos=flight.stream()
             .filter(fli -> fli.getNameAirline().toLowerCase().contains(name.toLowerCase()))
             .collect(Collectors.toList());
     int start = (page - 1) * size;
     int end = Math.min(start + size, flightPaginateDTos.size());
     List<FlightPaginateDTo> paginatedFlights = flightPaginateDTos.subList(start, end);
     model.put("Flight",paginatedFlights);
     model.put("totalPages", (int) Math.ceil((double) flightPaginateDTos.size() / size));
     model.put("currentPage", page);
     return "Admin/Flight/Flight";
 }
}
