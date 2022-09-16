package com.leard.usernote;

import com.leard.usernote.entity.UserEntity;
import com.leard.usernote.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UserNoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserNoteApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    CommandLineRunner runner(UserService userService){
        return args -> {
            userService.saveUser(
                    new UserEntity("thang.tn", "123456")
            );
        };
    }
}
