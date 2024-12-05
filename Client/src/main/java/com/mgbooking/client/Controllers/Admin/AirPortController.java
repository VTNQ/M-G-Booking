package com.mgbooking.client.Controllers.Admin;

import com.mgbooking.client.Configuration.GetToken;
import com.mgbooking.client.DTO.*;
import com.mgbooking.client.Services.AirPortService;
import com.mgbooking.client.Services.AuthService;
import com.mgbooking.client.Services.CityService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @GetMapping("AirPort/add")
    public String AddAirport(ModelMap model, HttpServletRequest request) {
        model.put("Airport", new AirPortDTO());
        String token = getToken.getTokenFromCookies(request);
        AccountDto accountDto=authService.FindByAccount(token);
        model.put("City",cityService.findAllCities(token,accountDto.getCountryId()));
        return "Admin/AirPort/add";
    }
@GetMapping("AirPort")
    public String AirPort(ModelMap model, HttpServletRequest request,@RequestParam(defaultValue = "1") int page,
                          @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "")String name ) {
    String token=getToken.getTokenFromCookies(request);
    AccountDto accountDto=authService.FindByAccount(token);
    List<Airport> airports=airPortService.FindAllByCountry(token,accountDto.getCountryId());
    List<Airport>filterAirports=airports.stream().filter(city-> city.getName().toLowerCase().contains(name.toLowerCase()))
            .collect(Collectors.toList());
    int start = (page - 1) * size;
    int end = Math.min(start + size, filterAirports.size());
    List<Airport> paginatedAirport = filterAirports.subList(start, end);
    model.put("Airport",paginatedAirport);
    model.put("totalPages", (int) Math.ceil((double) filterAirports.size() / size));
    model.put("currentPage", page);
    model.put("token",token);
    return "Admin/AirPort/AirPort";

}
}
