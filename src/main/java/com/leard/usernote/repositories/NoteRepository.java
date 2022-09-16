package com.leard.usernote.repositories;

import com.leard.usernote.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoteRepository extends JpaRepository<NoteEntity, String> {
    @Query(
        value = "SELECT * FROM note WHERE userId=?1 and noteTitle=?2",
        nativeQuery = true
    )
    List<NoteEntity> findByNoteTitle(String userId, String noteTitle);
    List<NoteEntity> findByUserId(String userId);
}
