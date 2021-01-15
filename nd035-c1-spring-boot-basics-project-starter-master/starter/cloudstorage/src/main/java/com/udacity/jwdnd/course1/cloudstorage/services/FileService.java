package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;

import java.util.List;

public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public void insert(File file) {
        fileMapper.insert(file);
    }

    public List<File> getFiles(String username) {
        return fileMapper.getFiles(username);
    }

    public File download(Integer fileId) {
        return fileMapper.download(fileId);
    }

    public void delete(Integer fileId) {
        fileMapper.delete(fileId);
    }
}
