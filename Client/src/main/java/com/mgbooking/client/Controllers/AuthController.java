package com.mgbooking.client.Controllers;

import com.mgbooking.client.DTO.LoginDTO;
import com.mgbooking.client.Services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping({"", "/"})
public class AuthController {
    @Autowired
    private AuthService authService;
    @GetMapping("ForgotPassword")
    public String ForgotPassword(ModelMap model) {
        return "User/Forgot/ForgotPassword";
    }
    @GetMapping("Login")
    public String Home(ModelMap modelMap) {
        modelMap.put("login", new LoginDTO());
        return "User/login/login";
    }
    @GetMapping("LoginAdmin")
    public String LoginAdmin(ModelMap modelMap) {
        modelMap.put("login", new LoginDTO());
        return "SuperAdmin/Login/login";
    }
    @PostMapping("LoginAdmin")
    public String LoginAdmin(@ModelAttribute("login")LoginDTO login, HttpServletResponse session,HttpServletRequest request, ModelMap modelMap) {
        try {
            String redirectUrl=authService.login(login,session,request);
            if(session!=null){

                return  redirectUrl;
            }else{
                return "redirect:/LoginAdmin";
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("Login")
    public String Login(@ModelAttribute("login") LoginDTO loginDTO, ModelMap modelMap, HttpServletRequest request, HttpServletResponse session) {
        try {
            String redirectUrl = authService.login(loginDTO,session,request);
            if (redirectUrl != null) {
                return redirectUrl;
            } else {
                String referer = request.getHeader("Referer");
                return "redirect:" + referer;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
