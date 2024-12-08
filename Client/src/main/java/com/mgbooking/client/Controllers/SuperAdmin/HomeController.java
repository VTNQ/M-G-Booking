package com.mgbooking.client.Controllers.SuperAdmin;

import com.mgbooking.client.Configuration.GetToken;
import com.mgbooking.client.Services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller("superAdminHomeController")
@RequestMapping({"/SuperAdmin"})
public class HomeController {
    @Autowired
    private GetToken tokenService;
    @Autowired
    private AuthService authService;
    @GetMapping("Home")
    public String Home(){
        return "SuperAdmin/Home/index";
    }
    @GetMapping("Logout")
    public String Logout(HttpServletRequest request, HttpServletResponse response){
        String token=tokenService.getTokenFromCookies(request);
        Object creationResult = authService.Logout(token,response);
        Map<String, Object> resultMap = (Map<String, Object>) creationResult;
        Object statusObj = resultMap.get("status");
        int status = 0;
        if (statusObj instanceof Double) {
            status = ((Double) statusObj).intValue();
        } else if (statusObj instanceof Integer) {
            status = (Integer) statusObj;
        }
        if (status == 200) {
            return "redirect:/LoginAdmin";
        }else{
            String referer = request.getHeader("Referer");
            if (referer != null) {
                return "redirect:" + referer;
            } else {
                return "redirect:/";
            }
        }
    }
}
