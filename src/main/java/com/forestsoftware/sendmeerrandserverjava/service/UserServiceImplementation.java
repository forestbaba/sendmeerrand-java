package com.forestsoftware.sendmeerrandserverjava.service;

import com.forestsoftware.sendmeerrandserverjava.Exceptions.AppException;
import com.forestsoftware.sendmeerrandserverjava.Request.LoginRequest;
import com.forestsoftware.sendmeerrandserverjava.Request.SignupRequest;
import com.forestsoftware.sendmeerrandserverjava.Request.UpdateProfileRequest;
import com.forestsoftware.sendmeerrandserverjava.Response.ApiResponse;
import com.forestsoftware.sendmeerrandserverjava.models.Role;
import com.forestsoftware.sendmeerrandserverjava.models.RoleName;
import com.forestsoftware.sendmeerrandserverjava.models.UpdatePassword;
import com.forestsoftware.sendmeerrandserverjava.models.User;
import com.forestsoftware.sendmeerrandserverjava.repository.RoleRepository;
import com.forestsoftware.sendmeerrandserverjava.repository.UserRepository;
import com.forestsoftware.sendmeerrandserverjava.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.*;

@Service
public class UserServiceImplementation  implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public ResponseEntity<?> createUser(SignupRequest user) {

        Optional isexisting = userRepository.findByEmailOrUsernameOrPhoneNumber(user.getEmail(),user.getUsername(), user.getUsername());
        if(isexisting.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AppException("User email, username or phonenumber already exist"));
        }


        User newUserObject = new User();
        newUserObject.setEmail(user.getEmail());
        newUserObject.setPassword(passwordEncoder.encode(user.getPassword()));
        newUserObject.setUsername(user.getUsername());
        newUserObject.setPhoneNumber(user.getPhone());
        Role role;
        if(user.getRole().equalsIgnoreCase("admin")){
            newUserObject.setActive(true);
            role = roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(() -> new AppException("User role not set"));
        }else if(user.getRole().equalsIgnoreCase("seller")){
            role = roleRepository.findByName(RoleName.ROLE_SELLER).orElseThrow(()-> new AppException("User role not set"));
        }
        else {
            role = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User role not set"));
        }
        newUserObject.setRole(Collections.singleton(role));
        User createdUser =  userRepository.save(newUserObject);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path("/api/users/{username}")
        .buildAndExpand(createdUser.getUsername()).toUri();

        return ResponseEntity.created(uri).body(new ApiResponse(true, "User created",newUserObject));
    }

    @Override
    public ResponseEntity login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);
        User user = userRepository.findUserByEmail(loginRequest.getEmail());
        user.setLastLogin(new Date());
        user.setLastLogin(new Date());
        userRepository.save(user);
        Map<String, Object> payload = new HashMap<>();
        payload.put("error", false);
        payload.put("message","Login success");
        payload.put("user", user);
        payload.put("token", jwt);
        return ResponseEntity.ok(payload);
    }

    @Override
    public ResponseEntity updatedPassword(UpdatePassword updatePassword, Principal principal) {
        Objects.requireNonNull(updatePassword.getOldPassword());
        Objects.requireNonNull(updatePassword.getNewPassword());
        try {
            User userPayload = returnUserByEmail(principal.getName());
            if(userPayload == null){
                throw new AppException("User is not valid");
            }

            boolean isoldPasswordCorrect = passwordEncoder.matches(updatePassword.getOldPassword(),userPayload.getPassword());
            if(isoldPasswordCorrect){
                userPayload.setPassword(passwordEncoder.encode(updatePassword.getNewPassword()));
                userRepository.save(userPayload);
                return ResponseEntity.ok(new ApiResponse(true,"Password updated successfully"));
            }else {
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false,"Old password does not match"));
            }
        }catch (BadCredentialsException e){
            throw new AppException("Invalid credentials");
        }
    }

    @Override
    public ResponseEntity getProfile(Principal principal) {
        User user = returnUserByEmail(principal.getName());
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity updateProfile(UpdateProfileRequest user, Principal principal) {
        User userPayload = returnUserByEmail(principal.getName());
        userPayload.setFirstName(user.getFirstName());
        userPayload.setLastName(user.getLastName());
        userPayload.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(userPayload);
        return ResponseEntity.ok(userPayload);
    }

    public User returnUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
}
