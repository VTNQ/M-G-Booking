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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @GetMapping("City")
    public String City(ModelMap model, HttpServletRequest request ){
        String token=tokenService.getTokenFromCookies(request);
        AccountDto account=authService.FindByAccount(token);
        model.put("Country",countryService.GetCountry(token));
        
        model.put("City",new CityDTO());
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
