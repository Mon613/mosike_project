package org.example.mosike_project.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class LoginController {
    @GetMapping("/login")
    private String loginForm(){
        return "admin/login_signin/loginForm";
    }
    @GetMapping("/sigin")
    private String signInForm(){
        return "admin/login_signin/signIn";
    }
}
