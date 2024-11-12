package com.mgbooking.client.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"","/"})
public class ErrorController {
    @GetMapping("404")
    public String error404() {
        return "User/404";
    }
}
