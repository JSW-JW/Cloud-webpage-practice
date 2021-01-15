package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FileMapper {

    @Insert("insert into files (filename, contenttype, filesize, userid, filedata) values (#{fileName}, #{contentType}, #{fileSize}, #{user.userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    Integer insert(File file);

    @Select("select * from files where fileid = #{fileId}")
    File download(Integer fileId);

    @Delete("delete files where fileid = #{fileId}")
    void delete(Integer fileId);

    @Select("select * from files where username = #{username}")
    List<File> getFiles(String username);
}
