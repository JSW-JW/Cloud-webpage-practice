package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper extends Rule {

    @Insert("INSERT INTO NOTES (noteTitle, noteDescription, userId) VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    Integer insert(Object add);

    @Select("select * from notes where noteId = #{noteId}")
    Note findById(Integer noteId);

    @Select("select * from notes where userId = #{userId}")
    List<Note> findAll(Integer userId);

    @Update("update notes set noteTitle = #{noteTitle}, noteDescription = #{noteDescription} where noteId = #{noteId}")
    Integer update(Object update);

    @Delete("delete notes where noteId = #{noteId}")
    Integer delete(Integer noteId);

}
