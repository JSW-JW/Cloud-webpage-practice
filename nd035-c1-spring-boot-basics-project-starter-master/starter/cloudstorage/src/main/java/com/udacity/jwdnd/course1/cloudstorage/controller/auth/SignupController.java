package com.udacity.jwdnd.course1.cloudstorage.controller.auth;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/signup")
@Controller
public class SignupController {

    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getSignup(User user, Model model) {
        return "signup";
    }

    @PostMapping()
    public String postSignup(User user, Model model) {
        if (user.getPassword() == null || user.getUsername() == null || user.getFirstName() == null || user.getLastName() == null) {
            model.addAttribute("errorMsg", "All fields are mandatory.");
            return "signup";
        }
        userService.createUser(user);
        model.addAttribute("successMsg", "You successfully signed up! Please continue to the login page.");
        return "signup";
    }
}
