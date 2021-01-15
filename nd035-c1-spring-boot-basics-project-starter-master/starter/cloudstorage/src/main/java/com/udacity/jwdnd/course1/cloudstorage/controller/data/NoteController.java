package com.udacity.jwdnd.course1.cloudstorage.controller.data;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/home")
@Controller
public class NoteController {

    private UserService userService;
    private NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @GetMapping(params = "get_notes_list")
    public String getNotesList(Model model, Authentication authentication) {
        List<Note> notes = noteService.getNotes(authentication.getName());
        model.addAttribute("notes", notes);
        return "home";
    }

    @PostMapping(params = "add_note")
    public String addNote(Authentication authentication, Note note, Model model) {
        User user = userService.getUser(authentication.getName());
        note.setUser(user);
        noteService.insert(note);
        return "result";
    }

    @PostMapping(params = "update_note")
    public String updateNote(Note note) {
        note.setNoteTitle(note.getNoteTitle());
        note.setNoteDescription(note.getNoteDescription());
        return "home";
    }

    @GetMapping(value = "/delete", params = "delete_note")
    public String deleteNote(@RequestParam("noteId") Integer noteId) {
        noteService.delete(noteId);
        return "home";
    }
}
