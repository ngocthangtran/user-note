package com.leard.usernote.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "note")
public class NoteModel {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID noteId;
    private String noteTitle;
    @Column(columnDefinition = "nvarchar(20000)")
    private String noteContent;
    @OneToMany
    @JoinColumn(name = "noteId")
    private List<AttachModel> listAttach = new ArrayList<>();
}
