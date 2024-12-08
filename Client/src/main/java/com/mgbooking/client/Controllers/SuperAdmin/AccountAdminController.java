package com.mgbooking.client.Controllers.SuperAdmin;

import com.mgbooking.client.Configuration.GetToken;
import com.mgbooking.client.DTO.Account.AccountAdmin;
import com.mgbooking.client.DTO.Account.RegisterAdmin;
import com.mgbooking.client.DTO.ListFlightDto;
import com.mgbooking.client.DTO.RegisterUser;
import com.mgbooking.client.Services.CountryService;
import com.mgbooking.client.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping({"/SuperAdmin"})
public class AccountAdminController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private UserService userService;
    @Autowired
    private GetToken tokenService;
    @GetMapping("AccountAdmin/add")
    public String AdminAccount(ModelMap modelMap, HttpServletRequest request){
        String token=tokenService.getTokenFromCookies(request);
        RegisterAdmin registerUser=new RegisterAdmin();
        modelMap.put("Country",countryService.GetCountry(token));
        modelMap.put("registerUser",registerUser);
        return "/SuperAdmin/AccountAdmin/add";
    }
    @GetMapping("AccountAdmin")
    public String AccountAdmin(ModelMap modelMap, HttpServletRequest request,@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "")String name){
        String token=tokenService.getTokenFromCookies(request);
        List<AccountAdmin>admins=userService.GetAccountAdmin(token);
        List<AccountAdmin>filteredAccount=admins.stream().filter(airline->
                airline.getFullName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
        int start = (page - 1) * size;
        int end = Math.min(start + size, filteredAccount.size());
        List<AccountAdmin>paginatedAdmin=filteredAccount.subList(start, end);
        modelMap.put("Account",paginatedAdmin);
        modelMap.put("totalPages", (int) Math.ceil((double) filteredAccount.size() / size));
        modelMap.put("currentPage", page);
        return "/SuperAdmin/AccountAdmin/AccountAdmin";
    }
    @PostMapping("AccountAdmin/add")
    public String AddAccount(ModelMap modelMap, HttpServletRequest request, @ModelAttribute("registerUser") RegisterAdmin registerUser
    , RedirectAttributes redirectAttributes){
        String token = tokenService.getTokenFromCookies(request);
        Object createObject=userService.RegisterAdmin(token,registerUser);
        Map<String, Object> resultMap = (Map<String, Object>) createObject;
        Object statusObj = resultMap.get("status");
        int status = 0;
        if (statusObj instanceof Double) {
            status = ((Double) statusObj).intValue();
        } else if (statusObj instanceof Integer) {
            status = (Integer) statusObj;
        }
        String message=resultMap.get("message").toString();
        if(status==200){
            redirectAttributes.addFlashAttribute("message",message);
            redirectAttributes.addFlashAttribute("messageType","success");
            return "redirect:/SuperAdmin/AccountAdmin/add";

        }else{
            redirectAttributes.addFlashAttribute("message",message);
            redirectAttributes.addFlashAttribute("messageType","error");
            return "redirect:/SuperAdmin/AccountAdmin/add";
        }
    }
}
