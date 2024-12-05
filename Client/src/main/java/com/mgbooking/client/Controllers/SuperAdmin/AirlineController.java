package com.mgbooking.client.Controllers.SuperAdmin;

import com.mgbooking.client.Configuration.GetToken;
import com.mgbooking.client.DTO.*;
import com.mgbooking.client.Services.AirlineService;
import com.mgbooking.client.Services.AuthService;
import com.mgbooking.client.Services.CountryService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping({"/SuperAdmin"})
public class AirlineController {

    @Autowired
    private GetToken getToken;
    @Autowired
    private AirlineService airlineService;
    @Autowired
    private AuthService authService;
    @Autowired
    private CountryService countryService;
    @PostMapping("UpdateAirline")
    public String UpdateAirLine(@ModelAttribute("Airline") UpdateFlightDTO flightDTO, HttpServletRequest request, ModelMap model, @RequestParam("imageForm")MultipartFile multipartFile,RedirectAttributes redirectAttributes) {
        String token=getToken.getTokenFromCookies(request);
        if(airlineService.UpdateFlight(flightDTO,token,multipartFile)){
            redirectAttributes.addFlashAttribute("message","Update Airline successfully");
            redirectAttributes.addFlashAttribute("messageType","success");
            return "redirect:/SuperAdmin/UpdateAirline/"+flightDTO.getId();

        }else{
            redirectAttributes.addFlashAttribute("message","Update Airline failed");
            redirectAttributes.addFlashAttribute("messageType","error");
            return "redirect:/SuperAdmin/UpdateAirline/"+flightDTO.getId();

        }
    }

    @GetMapping("UpdateAirline/{id}")
    public String UpdateAirline(@PathVariable int id, ModelMap model,HttpServletRequest request) {
        String token=getToken.getTokenFromCookies(request);
        model.put("Country",countryService.GetCountry(token));
        model.put("Airline",airlineService.FindFlight(token,id));
        return "/SuperAdmin/Airline/UpdateAirline";
    }
    @GetMapping("Airline")
    public String Airline(ModelMap modelMap, HttpServletRequest request,@RequestParam(defaultValue = "1") int page,
                          @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "")String name){
        modelMap.put("Airline",new AirlineDTO());

        String token=getToken.getTokenFromCookies(request);
        List<ListFlightDto>airlines=airlineService.FindAll(token);
        List<ListFlightDto>filteredAirlines=airlines.stream().filter(airline->
                airline.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
        int start = (page - 1) * size;
        int end = Math.min(start + size, filteredAirlines.size());
        List<ListFlightDto>paginatedAirline=filteredAirlines.subList(start, end);

        modelMap.put("Airline",paginatedAirline);
        modelMap.put("totalPages", (int) Math.ceil((double) filteredAirlines.size() / size));
        modelMap.put("currentPage", page);
        modelMap.put("token",token);

        return "/SuperAdmin/Airline/Airline";
    }
    @GetMapping("Airline/add")
    public String addAirline(ModelMap modelMap,HttpServletRequest request){
        String token=getToken.getTokenFromCookies(request);
        modelMap.put("Airline",new AirlineDTO());
        modelMap.put("Country",countryService.GetCountry(token));
        return "/SuperAdmin/Airline/add";
    }
    @PostMapping("Airline/add")

    public String Airline(@ModelAttribute("Airline") AirlineDTO flightDTO, ModelMap modelMap, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String token = getToken.getTokenFromCookies(request);

        // Gọi phương thức tạo chuyến bay và lấy kết quả
        Object creationResult = airlineService.CreateFlight(flightDTO, token);

        // Ép kiểu kết quả về Map
        Map<String, Object> resultMap = (Map<String, Object>) creationResult;

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
            return "redirect:/SuperAdmin/Airline";  // Nếu status == 200, chuyển hướng
        } else {
            redirectAttributes.addFlashAttribute("message",message);
            redirectAttributes.addFlashAttribute("messageType","error");
            return "redirect:/SuperAdmin/Airline";  // Nếu không, cũng chuyển hướng
        }
    }

}
