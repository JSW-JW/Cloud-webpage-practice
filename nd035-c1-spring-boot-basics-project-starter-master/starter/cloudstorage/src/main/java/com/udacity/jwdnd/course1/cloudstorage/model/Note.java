package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Note {
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private User user;

    public Note() {
    }

    public Note(Integer noteId, String noteTitle, String noteDescription, User user) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.user = user;
    }
}
