package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public List<File> getFiles(Integer userId) {
        return fileMapper.getFiles(userId);
    }

    public File getByFileId(Integer fileId) {
        return fileMapper.getByFileId(fileId);
    }

    public void insert(File file) {
        fileMapper.insert(file);
    }

    public File download(Integer fileId) {
        return fileMapper.download(fileId);
    }

    public void delete(Integer fileId) {
        fileMapper.delete(fileId);
    }
}
