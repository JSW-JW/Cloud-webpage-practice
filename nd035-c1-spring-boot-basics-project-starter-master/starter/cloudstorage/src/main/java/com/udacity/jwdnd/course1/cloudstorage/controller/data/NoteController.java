package com.udacity.jwdnd.course1.cloudstorage.controller.data;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/note")
@Controller
public class NoteController {

    private UserService userService;
    private NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping("/save")
    public String save(RedirectAttributes redirectAttributes, Authentication authentication, Note note) {
        if (note.getNoteId() == null) {
            User user = userService.getUser(authentication.getName());
            note.setUserId(user.getUserId());
            noteService.insert(note);
        } else {
            noteService.update(note);
        }
        redirectAttributes.addAttribute("reqPage", "notePage");
        return "redirect:/home";
    }

    @GetMapping("/delete")
    public String deleteNote(RedirectAttributes redirectAttributes, @RequestParam("noteId") Integer noteId, Authentication authentication) {
        String authUsername = authentication.getName();
        Integer authUserId = userService.getUser(authUsername).getUserId();
        Note findNote = noteService.findNoteById(noteId);
        if (!findNote.getUserId().equals(authUserId)) { // secure delete process from unrelated user.
            return "redirect:/home";
        }
        noteService.delete(noteId);
        redirectAttributes.addAttribute("reqPage", "notePage");
        return "redirect:/home";
    }
}
