package com.mgbooking.client.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("ProfileUserController")
@RequestMapping({"","/"})
public class ProfileController {
    @GetMapping("Profile")
    public String Profile(ModelMap modelMap){
        modelMap.addAttribute("cssPath", "/user/assets/css/argon-dashboard-tailwind.css?v=1.0.1");
        return "/User/Profile/Profile";
    }
}
