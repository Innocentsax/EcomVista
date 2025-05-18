package com.InnocentUdo.controller;

import com.InnocentUdo.DTO.request.SignupRequest;
import com.InnocentUdo.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @PostMapping("signup")
    public ResponseEntity<User> createUserHandler(@RequestBody SignupRequest signupRequest){
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setFullName(signupRequest.getFullName());
        return ResponseEntity.ok(user);
    }
}
