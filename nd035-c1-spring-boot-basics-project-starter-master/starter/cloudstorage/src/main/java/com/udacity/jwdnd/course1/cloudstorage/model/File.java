package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

@Getter @Setter
public class File {

    private Integer fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private Blob fileData;
    private User user;

    public File() {
    }

    public File(Integer fileId, String fileName, String contentType, String fileSize, Blob fileData, User user) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.fileData = fileData;
        this.user = user;
    }
}
