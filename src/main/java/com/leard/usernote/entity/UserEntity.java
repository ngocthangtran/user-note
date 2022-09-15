package com.leard.usernote.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user")
@Data

public class UserEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "userId", columnDefinition = "char(40)")
    private String userId;
    @Column(nullable = false, unique = true, name = "username")
    private String username;
    @Column(nullable = false, name = "password")
    private String password;
    @Column(nullable = true, name = "refreshToken")
    private String refreshToken;
    @OneToMany
    @JoinColumn(name = "userId")
    private List<NoteEntity> listNote = new ArrayList<>();
    public UserEntity(){}
    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
