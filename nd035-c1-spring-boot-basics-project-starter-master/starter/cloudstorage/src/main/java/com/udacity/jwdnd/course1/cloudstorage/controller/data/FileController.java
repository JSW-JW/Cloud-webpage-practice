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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/file")
@Controller
public class FileController {

    private FileService fileService;
    private UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public String addFile(File file, Model model) {
        fileService.insert(file);
        return "home";
    }

    @GetMapping("/download")
    public String downloadFile(File file, Model model) {
        return "home";
    }

    @GetMapping("/view")
    public String viewFileDetail(File file, Model model) {
        return "home";
    }

    @GetMapping("/delete")
    public String deleteFile(File file) {
        // TODO : check if the file belongs to authenticated user.

        fileService.delete(file.getFileId());
        return "redirect:/home";
    }
}
