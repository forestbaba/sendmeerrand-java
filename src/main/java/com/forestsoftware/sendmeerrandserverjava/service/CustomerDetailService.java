package com.forestsoftware.sendmeerrandserverjava.service;

import com.forestsoftware.sendmeerrandserverjava.models.Role;
import com.forestsoftware.sendmeerrandserverjava.models.RoleName;
import com.forestsoftware.sendmeerrandserverjava.models.User;
import com.forestsoftware.sendmeerrandserverjava.repository.UserRepository;
//import com.forestsoftware.sendmeerrandserverjava.security.UserPrincipalParent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomerDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String emailOrPhone) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(emailOrPhone);
        if(user == null){
          throw new UsernameNotFoundException("User not found with username or email : "+emailOrPhone);
        }
        return  new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    private Set getAuthority(User user) {
        Set authorities = new HashSet<>();
        user.getRole().forEach(role -> {authorities.add(new SimpleGrantedAuthority(""+role.getName()));});
        return authorities;
    }

    @Transactional
    public UserDetails loadUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("user not found with id : "+ id));
        return  new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
