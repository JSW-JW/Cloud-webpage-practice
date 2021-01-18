package com.udacity.jwdnd.course1.cloudstorage.controller.data;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/files")
@Controller
public class FileController {

    private FileService fileService;
    private UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping()
    public String getFilesList(Model model, Credential credential, Note note, File file, Authentication authentication) {
        User user = userService.getUser(authentication.getName());
        List<File> files = fileService.getFiles(user.getUserId());
        model.addAttribute("files", files);
        return "home";
    }

    @GetMapping(params = "nav_files")
    public String getFiles(Credential credential, Note note, File file) {
        System.out.println("here");
        return "home";
    }

    // TODO: request download file.

    // TODO: view file detail.

    @GetMapping("/delete")
    public String deleteFile(@RequestParam("fileId") Integer fileId) {
        // TODO : check if the file belongs to authenticated user.

        fileService.delete(fileId);
        return "redirect:/home";
    }
}
