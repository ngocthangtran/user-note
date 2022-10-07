package com.leard.usernote.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class JwtRequestModel implements Serializable {
    private String username;
    private String password;
    public JwtRequestModel() {
    }
    public JwtRequestModel(String username, String password) {
        super();
        this.username = username; this.password = password;
    }
}
