package com.leard.usernote.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "note")
@Data
public class NoteEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(columnDefinition = "char(40)")
    private String noteId;

    private String noteTitle;
    @Column(columnDefinition = "nvarchar(20000)")
    private String noteContent;
    @OneToMany
    @JoinColumn(name = "noteId")
    private List<AttachEntity> listAttach = new ArrayList<>();
    @Column(columnDefinition = "char(40)", nullable = false)
    private String userId;

}
