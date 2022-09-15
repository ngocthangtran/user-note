package com.leard.usernote.service;

import com.leard.usernote.exception.BadRequestException;
import com.leard.usernote.exception.NotFoundException;
import com.leard.usernote.entity.NoteEntity;
import com.leard.usernote.entity.UserEntity;
import com.leard.usernote.model.UserModel;
import com.leard.usernote.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public UserEntity findByUsername(String username) {
        UserEntity foundUser = userRepository.findByUsername(username);
        if(foundUser==null){
            throw new NotFoundException("Cannot find user with name "+ username);
        }
        return foundUser;
    }
    @Override
    public UserEntity saveUser(UserEntity user) {
//        validate user
        if(user.getUsername()==null){
            throw new BadRequestException("Username is require");
        }else if(user.getPassword()==null){
            throw new BadRequestException("Password is require!");
        }
//        validate user name exist
        UserEntity foundUser = userRepository.findByUsername(user.getUsername());
        if(foundUser!=null && foundUser.getUserId()!=null){
            throw new BadRequestException("Username is exist!");
        }
        UserEntity newUser = userRepository.save(user);
        return  newUser;
    }

    @Override
    public NoteEntity saveNote(NoteEntity user) {
        return null;
    }

    @Override
    public List<UserModel> getAllUser() {
        List<UserEntity> listUser = userRepository.findAll();
        if(listUser.size()==0){
            throw new NotFoundException("Cannot find user");
        }
        List<UserModel> listUserModels = new ArrayList<>();
        for (UserEntity user:listUser){
            listUserModels.add(new UserModel(user.getUserId(), user.getUsername(), user.getRefreshToken()));
        }
        return listUserModels;
    }

    @Override
    public void deleteUser(String userId) {
        Optional<UserEntity> userModel = userRepository.findById(userId);
        if(!userModel.isPresent()){
            throw new NotFoundException("Cannot find userId "+ userId);
        }
        try{
            userRepository.deleteById(userId);
        }catch (Exception e){
            System.out.println("Erorr api delete user");
            System.out.println(e.getMessage());
            throw new BadRequestException("Cannot delete user");
        }
    }
}
