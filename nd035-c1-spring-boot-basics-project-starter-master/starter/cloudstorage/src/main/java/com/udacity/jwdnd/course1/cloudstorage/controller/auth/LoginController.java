package com.udacity.jwdnd.course1.cloudstorage.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/login")
@Controller
public class LoginController {

    @GetMapping()
    public String getLogin(@RequestParam(value = "logoutMsg", required = false) String logoutMsg, @RequestParam(value = "errorMsg", required = false) String errorMsg, Model model) {
        if (errorMsg != null) { // get request param from the SecurityConfig.login.failureUrl
            model.addAttribute("errorMsg", "Invalid username or password");
        }
        if (logoutMsg != null) { // get request param from HomeController.
            model.addAttribute("logoutMsg", "You have been logged out");
        }
        return "login";
    }

    // Spring security automatically deals with all other things. It is okay just to set GetMapping().
}
