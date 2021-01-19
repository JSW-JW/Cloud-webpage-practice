package com.udacity.jwdnd.course1.cloudstorage.controller.data;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.TextUtils;

@RequestMapping("/credential")
@Controller
public class CredentialController {

    private UserService userService;
    private CredentialService credentialService;

    public CredentialController(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @PostMapping("/save")
    public String saveCredential(Model model, Credential credential) {
        if (credential.getCredentialId().toString().equals("")) {
            credentialService.insert(credential);
        }
        else {
            credentialService.update(credential);
        }
        return "home";
    }

    @GetMapping("/delete")
    public String deleteCredential(Credential credential) {
        credentialService.delete(credential);
        return "home";
    }
}
