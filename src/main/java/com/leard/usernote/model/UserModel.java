package com.leard.usernote.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
public class UserModel {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID userId;
    private String username;
    private String password;
    private String refreshToken;
    @OneToMany
    @JoinColumn(name = "userId")
    private List<NoteModel> listNote = new ArrayList<>();
}
