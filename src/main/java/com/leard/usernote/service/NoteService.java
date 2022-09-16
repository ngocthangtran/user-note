package com.leard.usernote.service;

import com.leard.usernote.entity.NoteEntity;

import java.util.List;

public interface NoteService {
    List<NoteEntity> getNodeByUser(String userId);
    NoteEntity createNoteByUser( NoteEntity note);
    NoteEntity updateNote(String userId, NoteEntity note);
    NoteEntity getNote(String userId);
    Void deleteNote(String userId, String noteId);
}
