package com.mgbooking.client.Controllers;

import com.mgbooking.client.Configuration.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping({"","/"})
public class AboutUsController {
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping("AboutUs")
    public String AboutUs(){
        try {
            return "User/AboutUs/AboutUs";
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }

    }
}
