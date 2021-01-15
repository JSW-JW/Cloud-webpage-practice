package com.udacity.jwdnd.course1.cloudstorage.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/login")
@Controller
public class LoginController {

    @GetMapping()
    public String getLogin() {
        return "login";
    }

    // Spring security automatically deals with all other things. It is okay just to set GetMapping().
}
