package com.mgbooking.client.Controllers.SuperAdmin;

import com.mgbooking.client.Configuration.GetToken;
import com.mgbooking.client.DTO.Country;
import com.mgbooking.client.Services.CountryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller("superAdminCountryController")
@RequestMapping({"/SuperAdmin"})
public class CoutryControlller {
    @Autowired
    private CountryService countryService;
    @Autowired
    private GetToken getToken;
    @GetMapping("Country")
    public String Country(ModelMap modelMap,HttpServletRequest request ,  @RequestParam(defaultValue = "1") int page,
                          @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "")String name){

        String token=getToken.getTokenFromCookies(request);
        List<Country> countries = countryService.GetCountry(token);
        List<Country> filteredCountries = countries.stream()
                .filter(country -> country.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        int start = (page - 1) * size;
        int end = Math.min(start + size, filteredCountries.size());
        List<Country> paginatedCountries = filteredCountries.subList(start, end);
        modelMap.put("Country", paginatedCountries);
        modelMap.put("totalPages", (int) Math.ceil((double) filteredCountries.size() / size));
        modelMap.put("currentPage", page);
        modelMap.put("token",token);
        modelMap.put("searchName", name);
    return  "/SuperAdmin/Country/Country";
    }

    @GetMapping("EditCountry/{id}")
    public String EditCountry(ModelMap modelMap, @PathVariable int id, HttpServletRequest request ){
        String token=getToken.getTokenFromCookies(request);
        modelMap.put("Country",countryService.FindCountry(token,id));
        return "/SuperAdmin/Country/UpdateCountry";
    }
    @GetMapping("DeleteCountry/{id}")
    public String DeleteCountry(ModelMap modelMap, @PathVariable int id, HttpServletRequest request ,RedirectAttributes redirectAttributes){
        try {
            String token=getToken.getTokenFromCookies(request);
            boolean success=countryService.DeleteCountry(token,id);
            if(success){
                redirectAttributes.addFlashAttribute("message","Delete Country successfully");
                redirectAttributes.addFlashAttribute("messageType","success");
            }else{
                redirectAttributes.addFlashAttribute("message","Failed to Delete Country");
                redirectAttributes.addFlashAttribute("messageType","error");
            }
            return "redirect:/SuperAdmin/Country";
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @PostMapping("UpdateCountry")
    public String UpdateCountry(ModelMap modelMap, @ModelAttribute("Country") Country country ,HttpServletRequest request,RedirectAttributes redirectAttributes){
        String token=getToken.getTokenFromCookies(request);
        boolean success=countryService.UpdateCountry(token,country);
        if(success){
            redirectAttributes.addFlashAttribute("message","Update Country successfully");
            redirectAttributes.addFlashAttribute("messageType","success");
        }else{
            redirectAttributes.addFlashAttribute("message","Failed to Update Country");
            redirectAttributes.addFlashAttribute("messageType","error");
        }
        return "redirect:/SuperAdmin/EditCountry/"+country.getId();
    }
    @GetMapping("Country/add")
    public String AddCountry(ModelMap modelMap){
        try {
            modelMap.put("Country", new Country());
            return "SuperAdmin/Country/add";
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @PostMapping("Country/add")
    public String Country(@ModelAttribute("Country") Country country, HttpServletRequest request, RedirectAttributes redirectAttributes){
        String token=getToken.getTokenFromCookies(request);
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
