package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getNotes(Integer userId) {
        return noteMapper.getNotes(userId);
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

    public Note findNoteById(Integer id) {
        return noteMapper.findById(id);
    }
}
