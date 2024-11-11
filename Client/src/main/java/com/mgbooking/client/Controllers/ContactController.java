package com.mgbooking.client.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"","/"})
public class ContactController {
    @GetMapping("Contact")
    public String Contact(){
        return "User/Contact/Contact";
    }
}
