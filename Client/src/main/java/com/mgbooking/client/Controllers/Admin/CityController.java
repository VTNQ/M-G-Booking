package com.mgbooking.client.Controllers.Admin;

import com.mgbooking.client.Configuration.GetToken;
import com.mgbooking.client.DTO.AccountDto;
import com.mgbooking.client.DTO.CityDTO;
import com.mgbooking.client.Services.AuthService;
import com.mgbooking.client.Services.CityService;
import com.mgbooking.client.Services.CountryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @GetMapping("City")
    public String City(ModelMap model, HttpServletRequest request ){
        String token=tokenService.getTokenFromCookies(request);
        AccountDto account=authService.FindByAccount(token);
        CityDTO cityDTO=new CityDTO();
        cityDTO.setCountry_id(account.getCountryId());
        model.put("City",cityDTO);
        model.put("token",token);
        return "/Admin/City/City";
    }
    @PostMapping("City")
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
