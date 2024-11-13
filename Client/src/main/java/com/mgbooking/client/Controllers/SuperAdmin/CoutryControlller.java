package com.mgbooking.client.Controllers.SuperAdmin;

import com.mgbooking.client.DTO.Country;
import com.mgbooking.client.Services.CountryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller("superAdminCountryController")
@RequestMapping({"/SuperAdmin"})
public class CoutryControlller {
    @Autowired
    private CountryService countryService;
    @GetMapping("Country")
    public String Country(ModelMap modelMap, @RequestParam(defaultValue = "0")int page,@RequestParam(defaultValue = "10")int size,HttpServletRequest request ){
        modelMap.put("Country",new Country());
        modelMap.put("token",(String)request.getSession().getAttribute("accessToken"));
    return  "/SuperAdmin/Country/Country";
    }
    @PostMapping("Country")
    public String Country(@ModelAttribute("Country") Country country, HttpServletRequest request, RedirectAttributes redirectAttributes){
        String token=(String)request.getSession().getAttribute("accessToken");
        boolean success=countryService.CreateCountry(token,country);
        if(success){
            redirectAttributes.addFlashAttribute("message","Country created successfully");
            redirectAttributes.addFlashAttribute("messageType","success");
        }else{
            redirectAttributes.addFlashAttribute("message","Failed to Create Country");
            redirectAttributes.addFlashAttribute("messageType","error");
        }
        return "redirect:/SuperAdmin/Country";
    }
}
