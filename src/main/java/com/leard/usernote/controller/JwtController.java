package com.leard.usernote.controller;

import com.leard.usernote.entity.ResponeObject;
import com.leard.usernote.filter.TokenManager;
import com.leard.usernote.model.JwtRequestModel;
import com.leard.usernote.model.JwtResponseModel;
import com.leard.usernote.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
@CrossOrigin
public class JwtController {
    @Autowired
    private UserServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenManager tokenManager;
    @PostMapping("/login")
    public ResponseEntity<ResponeObject> createToken(@RequestBody JwtRequestModel request) throws Exception {
        System.out.println(request.getUsername());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword())
            );
        } catch (DisabledException e) {
            log.error("USER_DISABLED: {}", e);
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            log.error("INVALID_CREDENTIALS: {}", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponeObject("NG", "Wrong username or password", null));
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        log.info("User details: {}", userDetails);
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        Map<String, String> token = new HashMap<>();
        token.put("token", jwtToken);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponeObject("OK", "Login successfull", token));
    }
}
