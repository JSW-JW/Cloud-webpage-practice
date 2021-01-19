package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    @Insert("insert into files (fileName, contentType, fileSize, userId, fileData) values (#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer insert(File file);

    @Select("select * from files where fileId = #{fileId}")
    File download(Integer fileId);

    @Delete("delete files where fileId = #{fileId}")
    void delete(Integer fileId);

    @Select("select * from files where userId = #{userId}")
    List<File> getFiles(Integer userId);

    @Select("select * from files where fileId = #{fileId}")
    File getByFileId(Integer fileId);
}
