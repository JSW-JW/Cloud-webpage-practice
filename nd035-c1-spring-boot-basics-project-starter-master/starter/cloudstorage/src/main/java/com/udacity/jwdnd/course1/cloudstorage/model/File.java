package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class File {

    private Integer fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private Byte fileData;
    private Integer userId;

    public File() {
    }

    public File(Integer fileId, String fileName, String contentType, String fileSize, Byte fileData, Integer userId) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.fileData = fileData;
        this.userId = userId;
    }
}
