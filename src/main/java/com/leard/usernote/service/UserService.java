package com.leard.usernote.service;

import com.leard.usernote.entity.NoteEntity;
import com.leard.usernote.entity.UserEntity;
import com.leard.usernote.model.UserModel;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserEntity findByUsername(String username);

    UserEntity saveUser(UserEntity user);
    NoteEntity saveNote(NoteEntity user);
    List<UserModel> getAllUser();
    void deleteUser(String userId);
}
