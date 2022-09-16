package com.leard.usernote.service;

import com.leard.usernote.entity.NoteEntity;
import com.leard.usernote.entity.UserEntity;
import com.leard.usernote.exception.BadRequestException;
import com.leard.usernote.exception.NotFoundException;
import com.leard.usernote.repositories.NoteRepository;
import com.leard.usernote.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService{
    private final NoteRepository noteRepository;
    private final UserService userService;
    @Override
    public List<NoteEntity> getNodeByUser(String userId) {
//        validate user id
        userService.validateUserId(userId);
        List<NoteEntity> listNote = noteRepository.findByUserId(userId);
        if(listNote.size()==0){
            throw new NotFoundException("You do not have note");
        }
        return  listNote;
    }

    @Override
    public NoteEntity createNoteByUser( NoteEntity note) {
//        validate user id
        userService.validateUserId(note.getUserId());
//        validate note title for user
        List<NoteEntity> foundNote = noteRepository.findByNoteTitle(note.getUserId(), note.getNoteTitle());
        if(foundNote.size()>0){
            throw new BadRequestException("You already have a note with this title");
        }
        return noteRepository.save(note);
    }

    @Override
    public NoteEntity updateNote(String userId, NoteEntity note) {
        return null;
    }

    @Override
    public NoteEntity getNote(String userId) {
        return null;
    }

    @Override
    public Void deleteNote(String userId, String noteId) {
        return null;
    }
}
