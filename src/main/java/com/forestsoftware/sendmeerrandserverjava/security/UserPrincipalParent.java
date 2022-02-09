package com.forestsoftware.sendmeerrandserverjava.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.forestsoftware.sendmeerrandserverjava.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrincipalParent implements UserDetails {
    private Long id;
    private String name;
    private String username;

    @JsonIgnore
    private String email;
//    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private User user;

    public UserPrincipalParent(User user) {
        this.user = user;
    }
//    public UserPrincipalParent(Long id, String name, String username, String email, String password, Collection<? extends GrantedAuthority>authorities) {
//        this.id = id;
//        this.name = name;
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.authorities = authorities;
//    }
//    public static UserPrincipalParent create(User user){
//        List<GrantedAuthority> authorities = user.getRole().stream().map(role ->
//                new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
//
//        return new UserPrincipalParent(user.getId(), user.getEmail(),
//                user.getPassword(),
//                user.getPhoneNumber(),user.getFirstName(),authorities);
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    public Long getId(){
        return id;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
