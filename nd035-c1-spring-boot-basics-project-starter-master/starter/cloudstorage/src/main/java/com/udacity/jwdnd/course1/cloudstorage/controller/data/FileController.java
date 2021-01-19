package com.udacity.jwdnd.course1.cloudstorage.controller.data;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.utils.CommonUtils;
import org.apache.catalina.connector.Response;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;


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
    public String addFile(@RequestParam("fileUpload") MultipartFile multi, Authentication authentication) throws IOException {
        Integer userId = userService.getUser(authentication.getName()).getUserId();
        String fileName = CommonUtils.getRandomString() + multi.getOriginalFilename();
        File file = new File(null, fileName, multi.getContentType(), String.valueOf(multi.getSize()), multi.getBytes(), userId);
        fileService.insert(file);
        return "redirect:/home";
    }

    @GetMapping(value = "/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("fileId") Integer fileId) {
        byte[] image = fileService.getByFileId(fileId).getFileData();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", "attachment; filename=\"" + fileId + ".jpg\"");
        headers.setContentLength(image.length);
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(image, headers, HttpStatus.OK);
    }

    @GetMapping("/view")
    public String viewFileDetail(File file, Model model) {
        return "home";
    }

    @GetMapping("/delete")
    public String deleteFile(@RequestParam("fileId") Integer fileId, Authentication authentication) {
        Integer authUserId = userService.getUser(authentication.getName()).getUserId();
        File findFile = fileService.getByFileId(fileId);
        if (!findFile.getUserId().equals(authUserId)) {  // check if the file belongs to authenticated user.
            return "redirect:/home";
        }
        fileService.delete(fileId);
        return "redirect:/home";
    }
}
