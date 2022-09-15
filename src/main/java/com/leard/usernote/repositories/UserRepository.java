package com.leard.usernote.repositories;

import com.leard.usernote.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, String> {


    UserEntity findByUsername(String username);
//    Eg query for Variable
//    @Query(
//            value = "SELECT * FROM user WHERE username=:username",
//            nativeQuery = true
//    )
//    List<UserModel> getAllUser(@Param("username")String username);
}
