package com.mgbooking.client.Controllers.Owner;

import com.mgbooking.client.Configuration.GetToken;
import com.mgbooking.client.DTO.AccountDto;
import com.mgbooking.client.DTO.Hotel.HotelDTO;
import com.mgbooking.client.DTO.Hotel.HotelListDto;
import com.mgbooking.client.DTO.Hotel.HotelUpdateDTO;
import com.mgbooking.client.Services.AuthService;
import com.mgbooking.client.Services.CityService;
import com.mgbooking.client.Services.HotelService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller("OwnerHotelController")
@RequestMapping("/Owner")
public class HotelController {
    @Autowired
    private CityService cityService;
    @Autowired
    private AuthService authService;
    @Autowired
    private HotelService hotelService;

    @Autowired
    private GetToken getToken;

    @GetMapping("Hotel/add")
    public String addHotel(ModelMap model, HttpServletRequest request) {
        String token = getToken.getTokenFromCookies(request);
        AccountDto accountDto = authService.FindByAccount(token);
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setOwnerId(accountDto.getId());
        model.put("City", cityService.findAllCities(token, accountDto.getCountryId()));
        model.put("hotel", hotelDTO);

        model.put("token", token);
        return "Owner/Hotel/add";
    }
    @PostMapping("Hotel/update")

    public String updateHotel(@ModelAttribute("hotel")HotelUpdateDTO hotelUpdateDTO,HttpServletRequest request,RedirectAttributes redirectAttributes,  @RequestParam(value = "imageForm", required = false) MultipartFile imageForm) {
        String token = getToken.getTokenFromCookies(request);
        Object createObject = hotelService.UpdateHotel(token, hotelUpdateDTO, imageForm);
        Map<String, Object> resultMap = (Map<String, Object>) createObject;
        Object statusObj = resultMap.get("status");
        int status = 0;
        if (statusObj instanceof Double) {
            status = ((Double) statusObj).intValue();
        } else if (statusObj instanceof Integer) {
            status = (Integer) statusObj;
        }
        String message = resultMap.get("message").toString();
        if (status == 200) {
            redirectAttributes.addFlashAttribute("message", message);
            redirectAttributes.addFlashAttribute("messageType", "success");
        
            return "redirect:/Owner/Hotel/"+hotelUpdateDTO.getId();
        } else {
            redirectAttributes.addFlashAttribute("message", message);
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/Owner/Hotel/"+hotelUpdateDTO.getId();
        }


    }
    @PostMapping("Hotel/UpdateMultipleImage/{id}")
    public String UpdateMultipleImage(@PathVariable("id")int id,@RequestParam(value = "MultiImage",required =false)List<MultipartFile>MultiImage,HttpServletRequest request,
    RedirectAttributes redirectAttributes){
        String token = getToken.getTokenFromCookies(request);
        Object createObject=hotelService.UpdateMultipleImages(token, id, MultiImage);
        Map<String, Object> resultMap = (Map<String, Object>) createObject;
        Object statusObj = resultMap.get("status");
        int status = 0;
        if (statusObj instanceof Double) {
            status = ((Double) statusObj).intValue();
        } else if (statusObj instanceof Integer) {
            status = (Integer) statusObj;
        }
        String message = resultMap.get("message").toString();
        if (status == 200) {
            redirectAttributes.addFlashAttribute("message", message);
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/Owner/Hotel/"+id;
        }else{
            redirectAttributes.addFlashAttribute("message", message);
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/Owner/Hotel/"+id;
        }

    }
    @GetMapping("Hotel/Detail/{id}")
    public String DetailHotel(@PathVariable("id")int id, ModelMap model, HttpServletRequest request) {
        String token = getToken.getTokenFromCookies(request);
        try {
            model.put("Image",hotelService.FindAllImages(token, id));
            return "Owner/Hotel/detail";
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("Hotel/{id}")
    public String editHotel(ModelMap model, @PathVariable("id") int id, HttpServletRequest request) {
        try {
            String token = getToken.getTokenFromCookies(request);
            AccountDto accountDto = authService.FindByAccount(token);
            model.put("hotel",hotelService.FindHotelById(token,id));
            model.put("City", cityService.findAllCities(token, accountDto.getCountryId()));
            model.put("ImageList",hotelService.FindAllImages(token, id));
            model.put("token", token);
            return "Owner/Hotel/edit";
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("Hotel")
    public String Hotel(ModelMap model, HttpServletRequest request,@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "")String name) {
        try {
            String token = getToken.getTokenFromCookies(request);
            List<HotelListDto>hotel=hotelService.FindAllHotels(token);
            List<HotelListDto>filteredHotel=hotel.stream().filter(hotels->
                    hotels.getName().toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
            int start = (page - 1) * size;
            int end = Math.min(start + size, filteredHotel.size());
            List<HotelListDto>paginatedHotel=filteredHotel.subList(start, end);
            model.put("hotel",paginatedHotel);
            model.put("totalPages", (int) Math.ceil((double) filteredHotel.size() / size));
            model.put("currentPage", page);
            return "Owner/Hotel/Hotel";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("Hotel/add")
    public String addHotel(@ModelAttribute("hotel") HotelDTO hotelDTO, ModelMap model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String token = getToken.getTokenFromCookies(request);
        Object createObject = hotelService.addHotel(token, hotelDTO);
        Map<String, Object> resultMap = (Map<String, Object>) createObject;
        Object statusObj = resultMap.get("status");
        int status = 0;
        if (statusObj instanceof Double) {
            status = ((Double) statusObj).intValue();
        } else if (statusObj instanceof Integer) {
            status = (Integer) statusObj;
        }
        String message = resultMap.get("message").toString();
        if (status == 200) {
            redirectAttributes.addFlashAttribute("message", message);
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/Owner/Hotel";
        } else {
            redirectAttributes.addFlashAttribute("message", message);
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/Owner/Hotel";
        }
    }
}
