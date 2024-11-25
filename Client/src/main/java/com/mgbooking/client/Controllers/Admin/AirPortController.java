package com.mgbooking.client.Controllers.Admin;

import com.mgbooking.client.Configuration.GetToken;
import com.mgbooking.client.DTO.AccountDto;
import com.mgbooking.client.DTO.AirPortDTO;
import com.mgbooking.client.DTO.CityDTO;
import com.mgbooking.client.Services.AirPortService;
import com.mgbooking.client.Services.AuthService;
import com.mgbooking.client.Services.CityService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller("AdminAirPortController")
@RequestMapping({"/Admin"})
public class AirPortController {
    @Autowired
    private GetToken getToken;
    @Autowired
    private CityService cityService;
    @Autowired
    private AuthService authService;
    @Autowired
    private AirPortService airPortService;
    @PostMapping("AirPort/UpdateAirPort")
    public String UpdateAirPort(@ModelAttribute("Airport")AirPortDTO airPortDTO,ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes){
        String token = getToken.getTokenFromCookies(request);
        Object createObject=airPortService.EditAirPort(token,airPortDTO);
        Map<String, Object> resultMap = (Map<String, Object>) createObject;

        // Lấy giá trị 'status' từ resultMap
        Object statusObj = resultMap.get("status");

        // Biến lưu giá trị status
        int status = 0; // Giá trị mặc định

        // Kiểm tra và chuyển kiểu từ Double sang Integer
        if (statusObj instanceof Double) {
            status = ((Double) statusObj).intValue();  // Chuyển từ Double sang int
        } else if (statusObj instanceof Integer) {
            status = (Integer) statusObj;  // Nếu status là Integer thì ép kiểu trực tiếp
        }

        // Kiểm tra giá trị của status và thực hiện điều hướng
        String message=resultMap.get("message").toString();
        if (status == 200) {

            redirectAttributes.addFlashAttribute("message",message);
            redirectAttributes.addFlashAttribute("messageType","success");
            return "redirect:/Admin/AirPort/Edit/"+airPortDTO.getId();  // Nếu status == 200, chuyển hướng
        } else {
            redirectAttributes.addFlashAttribute("message",message);
            redirectAttributes.addFlashAttribute("messageType","error");
            return "redirect:/Admin/AirPort/Edit/"+airPortDTO.getId();  // Nếu không, cũng chuyển hướng
        }
    }
    @GetMapping("AirPort/Edit/{id}")
    public String Edit(@PathVariable int id, ModelMap model,HttpServletRequest request) {
        String token=getToken.getTokenFromCookies(request);
        AirPortDTO airPortDTO=airPortService.FindByAirport(token,id);
        AccountDto accountDto=authService.FindByAccount(token);
        model.put("City",cityService.findAllCities(token,accountDto.getCountryId()));
        model.put("Airport", airPortDTO);
        return "Admin/AirPort/Edit";
    }
    @PostMapping("AirPort")
    public String AirPort(@ModelAttribute("Airport")AirPortDTO airport, ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes){
        String token = getToken.getTokenFromCookies(request);
        Object createObject=airPortService.CreateAirPort(token, airport);
        Map<String, Object> resultMap = (Map<String, Object>) createObject;

        // Lấy giá trị 'status' từ resultMap
        Object statusObj = resultMap.get("status");

        // Biến lưu giá trị status
        int status = 0; // Giá trị mặc định

        // Kiểm tra và chuyển kiểu từ Double sang Integer
        if (statusObj instanceof Double) {
            status = ((Double) statusObj).intValue();  // Chuyển từ Double sang int
        } else if (statusObj instanceof Integer) {
            status = (Integer) statusObj;  // Nếu status là Integer thì ép kiểu trực tiếp
        }

        // Kiểm tra giá trị của status và thực hiện điều hướng
        String message=resultMap.get("message").toString();
        if (status == 200) {

            redirectAttributes.addFlashAttribute("message",message);
            redirectAttributes.addFlashAttribute("messageType","success");
            return "redirect:/Admin/AirPort";  // Nếu status == 200, chuyển hướng
        } else {
            redirectAttributes.addFlashAttribute("message",message);
            redirectAttributes.addFlashAttribute("messageType","error");
            return "redirect:/Admin/AirPort";  // Nếu không, cũng chuyển hướng
        }
    }
@GetMapping("AirPort")
    public String AirPort(ModelMap model, HttpServletRequest request) {
    String token=getToken.getTokenFromCookies(request);
    AccountDto accountDto=authService.FindByAccount(token);
    model.put("IdCountry",accountDto.getCountryId());
    model.put("City",cityService.findAllCities(token,accountDto.getCountryId()));
    model.put("Airport",new AirPortDTO());
    model.put("token",token);
    return "Admin/AirPort/AirPort";

}
}
