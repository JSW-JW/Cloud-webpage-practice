package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Insert("insert into credentials (url, username, key, password, userId) values(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    Integer insert(Credential credential);

    @Select("select * from credentials where userId = #{userId}")
    List<Credential> findAll(Integer userId);

    @Update("update credentials set url = #{url}, username = #{username}, password = #{password} where credentialId = #{credentialId}")
    void update(Credential credential);

    @Delete("delete credentials where credentialId = #{credentialId}")
    void delete(Integer id);

}
