package com.udacity.jwdnd.course1.cloudstorage.controller.data;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/home")
@Controller
public class FileController {

    private FileService fileService;
    private UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping(params = "get_files_list")
    public String getFilesList(Model model, Authentication authentication) {
        List<File> files = fileService.getFiles(authentication.getName());
        model.addAttribute("files", files);
        return "home";
    }

    // TODO: request download file.

    // TODO: view file detail.

    @GetMapping(value = "/delete", params = "delete_file")
    public String deleteFile(@RequestParam("fileId") Integer fileId) {
        // TODO : check if the file belongs to authenticated user.

        fileService.delete(fileId);
        return "home";
    }
}
