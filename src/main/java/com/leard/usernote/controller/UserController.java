package com.leard.usernote.controller;

import com.leard.usernote.entity.ResponeObject;
import com.leard.usernote.entity.UserEntity;
import com.leard.usernote.model.UserModel;
import com.leard.usernote.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")

public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<ResponeObject> getUserByUsername(@PathVariable String username){
        UserEntity foundUser = userService.findByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponeObject("ok", "fined ", foundUser)
        );
    }
    @GetMapping("/")
    public ResponseEntity<ResponeObject> getUserAllUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());

        List<UserModel> listUser = userService.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponeObject("ok", "fined "+listUser.size()+" user", listUser)
        );
    }
    @PostMapping("/")
    public ResponseEntity<ResponeObject> createUser(@RequestBody UserEntity user){
        UserEntity newUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponeObject("ok", "User create successful", newUser)
        );
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponeObject> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponeObject("ok", "UserId "+ userId +" delete successful", null)
        );
    }
}