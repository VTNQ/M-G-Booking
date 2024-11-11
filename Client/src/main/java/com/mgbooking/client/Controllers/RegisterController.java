package com.mgbooking.client.Controllers;

import com.mgbooking.client.DTO.RegisterOwnerDto;
import com.mgbooking.client.DTO.RegisterUser;
import com.mgbooking.client.Services.CountryService;
import com.mgbooking.client.Services.OwnerService;
import com.mgbooking.client.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller()
@RequestMapping({"","/"})
public class RegisterController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private UserService userService;
    @Autowired
    private OwnerService ownerService;
    @GetMapping("RegisterUser")
    public String Register(ModelMap model){

        model.put("country",countryService.GetCountry());
        model.put("RegisterUser",new RegisterUser());
        return "User/Register/RegisterUser";
    }
    @PostMapping("RegisterUser")
    public String RegisterUser(@ModelAttribute("RegisterUser")RegisterUser registerUser){
        if(userService.RegisterUser(registerUser)){
            return "redirect:/RegisterUser";
        }else{
            return "redirect:/RegisterUser";
        }
    }
    @GetMapping("RegisterOwner")
    public  String RegisterOwner(ModelMap modelMap){
        modelMap.put("RegisterOwner",new RegisterOwnerDto());
        return "User/Register/RegisterOwner";
    }
    @PostMapping("RegisterOwner")
    public String RegisterOwner(@ModelAttribute("RegisterOwner")RegisterOwnerDto registerOwnerDto, RedirectAttributes redirectAttributes){

       boolean registrationSuccess=ownerService.RegisterOwner(registerOwnerDto);
       if(registrationSuccess){
           redirectAttributes.addFlashAttribute("toastrMessage", "Registration successful!");
           redirectAttributes.addFlashAttribute("toastrType", "success");  // Type could be success, error, info, etc.
           return "redirect:/RegisterOwner";
       }else{
           redirectAttributes.addFlashAttribute("toastrMessage", "Registration failed. Please try again.");
           redirectAttributes.addFlashAttribute("toastrType", "error");
           return "redirect:/RegisterOwner";
       }
    }
}
