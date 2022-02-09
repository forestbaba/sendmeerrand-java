package com.forestsoftware.sendmeerrandserverjava.controller;

import com.forestsoftware.sendmeerrandserverjava.Request.LoginRequest;
import com.forestsoftware.sendmeerrandserverjava.Request.SignupRequest;
import com.forestsoftware.sendmeerrandserverjava.Request.UpdateProfileRequest;
import com.forestsoftware.sendmeerrandserverjava.models.UpdatePassword;
import com.forestsoftware.sendmeerrandserverjava.models.User;
import com.forestsoftware.sendmeerrandserverjava.repository.UserRepository;
import com.forestsoftware.sendmeerrandserverjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

 @Autowired
 private UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity signup (@Valid @RequestBody SignupRequest signupRequest)  {
        return userService.createUser(signupRequest);
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }
    @PostMapping("/update-password")
    public ResponseEntity updatePassword(@Valid @RequestBody UpdatePassword updatePassword, Principal principal){
        return userService.updatedPassword(updatePassword, principal);
    }

    @GetMapping("/profile")
    public ResponseEntity getProfile(Principal principal){
        return userService.getProfile(principal);
    }

    @PostMapping("/update-profile")
    public ResponseEntity updateProfile(@Valid @RequestBody UpdateProfileRequest user, Principal principal){
        return userService.updateProfile(user, principal);
    }
}
