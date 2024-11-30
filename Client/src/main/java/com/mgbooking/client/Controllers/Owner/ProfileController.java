package com.mgbooking.client.Controllers.Owner;

import com.mgbooking.client.Configuration.GetToken;
import com.mgbooking.client.DTO.AccountDto;
import com.mgbooking.client.Services.AuthService;
import com.mgbooking.client.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller("ProfileOwnerController")
@RequestMapping("/Owner")
public class ProfileController {
    @Autowired
    private GetToken getToken;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserService userService;
    @GetMapping("Profile")
    public String Profile(HttpServletRequest request, ModelMap modelMap){
        String token=getToken.getTokenFromCookies(request);
        modelMap.put("account",authService.FindByAccount(token));
        return "Owner/Profile/Profile";
    }
    @PostMapping("UpdateProfile")
    public String UpdateProfile(HttpServletRequest request, ModelMap modelMap, @ModelAttribute("account")AccountDto accountDto
    , RedirectAttributes redirectAttributes){
        String token = getToken.getTokenFromCookies(request);
        Object createObject=userService.UpdateAccount(token,accountDto);
        Map<String, Object> resultMap = (Map<String, Object>) createObject;
        Object statusObj = resultMap.get("status");
        int status=0;
        if (statusObj instanceof Double) {
            status = ((Double) statusObj).intValue();
        } else if (statusObj instanceof Integer) {
            status = (Integer) statusObj;
        }
        String message=resultMap.get("message").toString();
        if (status == 200) {

            redirectAttributes.addFlashAttribute("message",message);
            redirectAttributes.addFlashAttribute("messageType","success");
            return "redirect:/Owner/Profile";
        }else{
            redirectAttributes.addFlashAttribute("message",message);
            redirectAttributes.addFlashAttribute("messageType","error");
            return "redirect:/Owner/Profile";
        }

    }
}
