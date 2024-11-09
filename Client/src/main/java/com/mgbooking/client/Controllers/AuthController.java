package com.mgbooking.client.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping({"","/"})
public class AuthController {
@GetMapping("Login")
    public String Home() {
return "User/login/login";
}
}
