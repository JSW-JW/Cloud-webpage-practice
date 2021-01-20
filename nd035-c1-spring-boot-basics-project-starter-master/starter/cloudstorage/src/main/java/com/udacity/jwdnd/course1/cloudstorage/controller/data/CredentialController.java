package com.udacity.jwdnd.course1.cloudstorage.controller.data;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String saveCredential(RedirectAttributes redirectAttributes, Model model, Credential credential, Authentication authentication) {
        if (credential.getCredentialId() == null) {
            Integer userId = userService.getUser(authentication.getName()).getUserId();
            credentialService.insert(credential, userId);
        }
        else {
            credentialService.update(credential);
        }
        redirectAttributes.addAttribute("reqPage", "credentialPage");
        return "redirect:/home";
    }

    @GetMapping("/delete")
    public String deleteCredential(RedirectAttributes redirectAttributes, @RequestParam("id") Integer credId) {
        credentialService.delete(credId);
        redirectAttributes.addAttribute("reqPage", "credentialPage");
        return "redirect:/home";
    }
}
