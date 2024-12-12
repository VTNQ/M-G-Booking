package com.mgbooking.client.Controllers.Owner;

import ch.qos.logback.core.model.Model;
import com.mgbooking.client.Configuration.GetToken;
import com.mgbooking.client.DTO.Service.ServiceDTO;
import com.mgbooking.client.Services.ServiceInterface;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/Owner")
public class ServiceController {
    @Autowired
    private GetToken tokenService;
    @Autowired
    private ServiceInterface service;
    @GetMapping("service/{id}")
    public String service(@PathVariable int id, ModelMap model, HttpSession session, HttpServletRequest request) {
        try {
            String token=tokenService.getTokenFromCookies(request);
            session.setAttribute("id",id);
            model.put("service",service.FindAllServices(token,id));
            return "/Owner/Service/Service";
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("service/edit/{id}")
    public String EditService(@PathVariable int id,ModelMap model,HttpServletRequest request) {
        try {
            String token=tokenService.getTokenFromCookies(request);
            model.put("service",service.FindAllServices(token,id));
            return "Owner/Service/Edit";
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @PostMapping("service/add")
    public String addService(@ModelAttribute("service") ServiceDTO serviceDTO, ModelMap model,HttpServletRequest request,
                             RedirectAttributes redirectAttributes) {
        try {
            String token=tokenService.getTokenFromCookies(request);
            Object createObject=service.AddService(token,serviceDTO);
            Map<String,Object> resultMap=(Map<String,Object>)createObject;
            Object statusObject=resultMap.get("status");
            int status=0;
            if(statusObject instanceof Double){
                status = ((Double) statusObject).intValue();
            }else if(statusObject instanceof Integer){
                status=(Integer)statusObject;
            }
            String message=resultMap.get("message").toString();
            if(status==200){
                redirectAttributes.addFlashAttribute("message",message);
                redirectAttributes.addFlashAttribute("messageType","success");
                return "redirect:/Owner/service/"+serviceDTO.getHotel_id();
            }else{
                redirectAttributes.addFlashAttribute("message",message);
                redirectAttributes.addFlashAttribute("messageType","error");
                return "redirect:/Owner/service/"+serviceDTO.getHotel_id();
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("service/add")
    public String add(ModelMap model,HttpSession session) {
        try {
            Integer id=(Integer)session.getAttribute("id");
            ServiceDTO serviceDTO=new ServiceDTO();
            if(id!=null){
                serviceDTO.setHotel_id(id);
            }
          model.put("service",serviceDTO);
            return "/Owner/Service/add";

        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
