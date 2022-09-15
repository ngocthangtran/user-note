package com.leard.usernote.model;

import lombok.Data;

import java.util.UUID;

@Data
public class UserModel {
    private String userId;
    private String username;
    private String refreshToken;

    public UserModel(String userId, String username, String refreshToken) {
        this.userId = userId;
        this.username = username;
        this.refreshToken = refreshToken;
    }
}
