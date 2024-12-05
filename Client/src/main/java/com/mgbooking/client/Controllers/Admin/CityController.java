package com.mgbooking.client.Controllers.Admin;

import com.mgbooking.client.Configuration.GetToken;
import com.mgbooking.client.DTO.AccountDto;
import com.mgbooking.client.DTO.City;
import com.mgbooking.client.DTO.CityDTO;
import com.mgbooking.client.DTO.FlightPaginateDTo;
import com.mgbooking.client.Services.AuthService;
import com.mgbooking.client.Services.CityService;
import com.mgbooking.client.Services.CountryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller("AdminCityController")
@RequestMapping({"/Admin"})
public class CityController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private CityService cityService;
    @Autowired
    private AuthService authService;
    @Autowired
    private GetToken tokenService;
    @GetMapping("City/{id}")
    public String DetailCity(ModelMap model, @PathVariable int id, HttpServletRequest request) {
        String token=tokenService.getTokenFromCookies(request);
        CityDTO cityFindDTO=cityService.FindCityById(token,id);
        model.put("city", cityFindDTO);
        return "Admin/City/Detail";
    }
    @GetMapping("DeleteCity/{id}")
    public String DeleteCity(ModelMap model, @PathVariable int id, HttpServletRequest request,RedirectAttributes redirectAttributes) {
        String token=tokenService.getTokenFromCookies(request);
        boolean success=cityService.DeleteCity(token,id);
        if(success){
            redirectAttributes.addFlashAttribute("message","Delete City successfully");
            redirectAttributes.addFlashAttribute("messageType","success");
        }else{
            redirectAttributes.addFlashAttribute("message","Failed to Delete City");
            redirectAttributes.addFlashAttribute("messageType","error");
        }
        return "redirect:/Admin/City";
    }
    @PostMapping("UpdateCity")
    public String UpdateCity(@ModelAttribute("City")CityDTO cityDTO, ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes){
        String token=tokenService.getTokenFromCookies(request);
        boolean success=cityService.UpdateCity(token,cityDTO);
        if(success){
            redirectAttributes.addFlashAttribute("message","Update City successfully");
            redirectAttributes.addFlashAttribute("messageType","success");
        }else{
            redirectAttributes.addFlashAttribute("message","Failed to Update City");
            redirectAttributes.addFlashAttribute("messageType","error");
        }
        return "redirect:/Admin/City/"+cityDTO.getId();
    }
    @GetMapping("City/add")
    public String AddCity(ModelMap model, HttpServletRequest request) {
        String token=tokenService.getTokenFromCookies(request);
        AccountDto accountDto=authService.FindByAccount(token);
        CityDTO cityDTO=new CityDTO();
        cityDTO.setCountry_id(accountDto.getCountryId());
        model.put("city", cityDTO);
        return "Admin/City/add";
    }
    @GetMapping("City")
    public String City(ModelMap model, HttpServletRequest request,@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "")String name ){
        String token=tokenService.getTokenFromCookies(request);
        AccountDto account=authService.FindByAccount(token);
        List<City>cities=cityService.findAllCities(token,account.getCountryId());
        List<City>filterCities=cities.stream().filter(city-> city.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        int start = (page - 1) * size;
        int end = Math.min(start + size, filterCities.size());
        List<City> paginatedCity = filterCities.subList(start, end);
        model.put("city",paginatedCity);
        model.put("totalPages", (int) Math.ceil((double) filterCities.size() / size));
        model.put("currentPage", page);
        model.put("token",token);
        return "/Admin/City/City";
    }
    @PostMapping("City/add")
    public String City(@ModelAttribute("City")CityDTO cityDTO, ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes){
        String token=tokenService.getTokenFromCookies(request);
        boolean success=cityService.CreateCity(token,cityDTO);
        if(success){
            redirectAttributes.addFlashAttribute("message","Create City successfully");
            redirectAttributes.addFlashAttribute("messageType","success");
        }else{
            redirectAttributes.addFlashAttribute("message","Failed to Create City");
            redirectAttributes.addFlashAttribute("messageType","error");
        }
        return "redirect:/Admin/City";
    }
}
