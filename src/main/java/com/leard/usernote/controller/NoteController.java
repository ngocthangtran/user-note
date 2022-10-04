package com.leard.usernote.controller;

import com.leard.usernote.entity.NoteEntity;
import com.leard.usernote.entity.ResponeObject;
import com.leard.usernote.entity.UserEntity;
import com.leard.usernote.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/note")
@RequiredArgsConstructor

public class NoteController {
    private final NoteService noteService;

    @GetMapping(path = "/")
    public ResponseEntity<ResponeObject> getNoteForUser(@RequestBody UserEntity user){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponeObject("ok", "", noteService.getNodeByUser(user.getUserId()))
        );
    }
    @PostMapping(path ="")
    public ResponseEntity<ResponeObject> createNote(@RequestBody NoteEntity noteEntity){
        NoteEntity note = noteService.createNoteByUser(noteEntity);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponeObject("ok", "Create node successful", note)
        );
    }
}
