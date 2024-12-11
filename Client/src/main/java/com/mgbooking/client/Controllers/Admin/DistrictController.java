package com.mgbooking.client.Controllers.Admin;

import com.mgbooking.client.Configuration.GetToken;
import com.mgbooking.client.DTO.District.District;
import com.mgbooking.client.DTO.District.DistrictDTO;
import com.mgbooking.client.Services.DistrictService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller("AdminDistrictController")
@RequestMapping({"/Admin"})
public class DistrictController {
    @Autowired
    private GetToken tokenService;
    @Autowired
    private DistrictService districtService;
    @GetMapping("District/{id}")
    public String District(ModelMap model, @PathVariable int id, HttpSession session, HttpServletRequest request,@RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "10") int size,@RequestParam(defaultValue = "")String name) {
        try {
            String token=tokenService.getTokenFromCookies(request);
            session.setAttribute("id",id);
            List<District>districts=districtService.getDistricts(token,id);
            List<District>districtsPaginateDTO=districts.stream()
                            .filter(di->di.getName().toLowerCase().contains(name.toLowerCase()))
                                    .collect(Collectors.toList());
            int start=(page-1)*size;
            int end=Math.min(start+size,districtsPaginateDTO.size());
            List<District>paginatedDistricts=districtsPaginateDTO.subList(start,end);
            model.put("District",paginatedDistricts);
            model.put("totalPages", (int) Math.ceil((double) districtsPaginateDTO.size() / size));
            model.put("id",id);
            model.put("currentPage", page);
            return "Admin/District/District";
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("District/add")
    public String Add(ModelMap modelMap,HttpSession session, HttpServletRequest request) {
        try {
            Integer id=(Integer)session.getAttribute("id");
            DistrictDTO districtDTO=new DistrictDTO();
            if(id!=null){
                districtDTO.setDistrictId(id);
            }
            modelMap.put("district",districtDTO);
            return "Admin/District/add";
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("District/edit/{id}")
    public String Edit(@PathVariable int id,HttpServletRequest request,ModelMap modelMap,HttpSession session) {
        try {
            String token=tokenService.getTokenFromCookies(request);
            modelMap.put("district",districtService.getDistrict(token,id));
            return "Admin/District/edit";
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @PostMapping("District/update")
    public String update(@ModelAttribute("district")DistrictDTO districtDTO,RedirectAttributes redirectAttributes,HttpSession session,HttpServletRequest request) {
        try {
            String token=tokenService.getTokenFromCookies(request);
            Object createObject=districtService.UpdateDistrict(token,districtDTO);
            Map<String,Object>resultMap=(Map<String,Object>)createObject;
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
                return "redirect:/Admin/District/edit/"+districtDTO.getId();
            }else{
                redirectAttributes.addFlashAttribute("message",message);
                redirectAttributes.addFlashAttribute("messageType","error");
                return "redirect:/Admin/District/edit/"+districtDTO.getId();
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @PostMapping("District/add")
    public String Add(@ModelAttribute("district")DistrictDTO districtDTO, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        try {
            String token=tokenService.getTokenFromCookies(request);
            Object createObject=districtService.addDistrict(token,districtDTO);
            Map<String,Object>resultMap=(Map<String,Object>)createObject;
            Object statusObj=resultMap.get("status");
            int status=0;
            if(statusObj instanceof Double){
                status = ((Double) statusObj).intValue();
            }else if(statusObj instanceof Integer){
                status=(Integer)statusObj;
            }
            String message=resultMap.get("message").toString();
            if(status==200){
                redirectAttributes.addFlashAttribute("message",message);
                redirectAttributes.addFlashAttribute("messageType","success");
                return "redirect:/Admin/District/"+districtDTO.getDistrictId();
            }else{
                redirectAttributes.addFlashAttribute("message",message);
                redirectAttributes.addFlashAttribute("messageType","error");
                return "redirect:/Admin/District/"+districtDTO.getDistrictId();
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
