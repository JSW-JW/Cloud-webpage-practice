package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface NoteMapper {

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{noteTitle}, #{noteDescription}, #{user.userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    Integer insert(Note note);

    @Select("select * from notes where username = #{username}")
    List<Note> getNotes(String username);

    @Update("update notes set notetitle = #{noteTitle}, notedescription = #{noteDescription} where noteId = #{noteId}")
    void update(Note note);

    @Delete("delete notes where noteid = #{noteId}")
    void delete(Integer noteId);

}
