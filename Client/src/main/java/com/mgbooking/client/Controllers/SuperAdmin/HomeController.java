package com.mgbooking.client.Controllers.SuperAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("superAdminHomeController")
@RequestMapping({"/SuperAdmin"})
public class HomeController {
    @GetMapping("Home")
    public String Home(){
        return "SuperAdmin/Home/index";
    }
}
