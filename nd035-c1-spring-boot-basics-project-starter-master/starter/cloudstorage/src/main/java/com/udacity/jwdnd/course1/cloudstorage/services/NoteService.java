package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;

import java.util.List;

public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getNotes(String username) {
        return noteMapper.getNotes(username);
    }

    public void insert(Note note) {
        noteMapper.insert(note);
    }

    public void update(Note note) {
        noteMapper.update(note);
    }

    public void delete(Integer noteId) {
        noteMapper.delete(noteId);
    }
}
