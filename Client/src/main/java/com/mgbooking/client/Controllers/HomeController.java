package com.mgbooking.client.Controllers;

import com.mgbooking.client.Configuration.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping({"","/"})
public class HomeController {
    @Autowired
    private JwtUtil jwtUtil;
    @GetMapping("Home")
    public String Home(HttpServletRequest Request) {
        String token=(String) Request.getSession().getAttribute("accessToken");

        if(token!=null && jwtUtil.extractRoles(token).contains("ROLE_ADMIN")){
            return "User/Home/Home";
        }
        
        return "redirect:/404" ;
    }
}
