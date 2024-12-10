package com.mgbooking.client.Controllers.Admin;

import com.mgbooking.client.Configuration.GetToken;
import com.mgbooking.client.Services.DistrictService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("AdminDistrictController")
@RequestMapping({"/Admin"})
public class DistrictController {
    @Autowired
    private GetToken tokenService;
    @Autowired
    private DistrictService districtService;
    @GetMapping("District/{id}")
    public String AddDistrict(ModelMap model, @PathVariable int id, HttpSession session, HttpServletRequest request) {
        try {
            String token=tokenService.getTokenFromCookies(request);
            session.setAttribute("id",id);
            model.put("District",districtService.getDistricts(token,id));
            return "Admin/District/District";
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
