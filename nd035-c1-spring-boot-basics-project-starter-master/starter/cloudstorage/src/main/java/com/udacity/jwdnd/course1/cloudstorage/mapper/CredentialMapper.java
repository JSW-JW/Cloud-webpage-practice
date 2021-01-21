package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper extends Rule {

    @Insert("insert into credentials (url, username, key, password, userId) values(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    Integer insert(Object add);

    @Select("select * from credentials where userId = #{userId}")
    List<Credential> findAll(Integer userId);

    @Select("select * from credentials where credentialId = #{credId}")
    Credential findById(Integer credId);

    @Update("update credentials set url = #{url}, username = #{username}, key = #{key}, password = #{password} where credentialId = #{credentialId}")
    Integer update(Credential credential);

    @Delete("delete credentials where credentialId = #{credentialId}")
    Integer delete(Integer id);

}
