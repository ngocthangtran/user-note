package com.leard.usernote.service;

import com.leard.usernote.exception.BadRequestException;
import com.leard.usernote.exception.NotFoundException;
import com.leard.usernote.entity.NoteEntity;
import com.leard.usernote.entity.UserEntity;
import com.leard.usernote.model.UserModel;
import com.leard.usernote.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service @RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public void validateUserId(String userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if(!userEntity.isPresent()){
            throw new NotFoundException("UserId invalid or not exist");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if(user==null){
//            return error user note found
            throw new UsernameNotFoundException("User not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(user.getUsername().equals("thang.tn")==true){
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }else {
            authorities.add(new SimpleGrantedAuthority("USER"));
        }
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
