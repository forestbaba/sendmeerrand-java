package com.forestsoftware.sendmeerrandserverjava.service;

import com.forestsoftware.sendmeerrandserverjava.Request.LoginRequest;
import com.forestsoftware.sendmeerrandserverjava.Request.SignupRequest;
import com.forestsoftware.sendmeerrandserverjava.Request.UpdateProfileRequest;
import com.forestsoftware.sendmeerrandserverjava.models.UpdatePassword;
import com.forestsoftware.sendmeerrandserverjava.models.User;
import org.springframework.http.ResponseEntity;

import java.security.Principal;

public interface UserService {
    ResponseEntity createUser(SignupRequest user);
    ResponseEntity login (LoginRequest loginRequest);
    ResponseEntity updatedPassword (UpdatePassword updatePassword, Principal principal);
    ResponseEntity getProfile(Principal principal);
    ResponseEntity updateProfile(UpdateProfileRequest user, Principal principal);
}
