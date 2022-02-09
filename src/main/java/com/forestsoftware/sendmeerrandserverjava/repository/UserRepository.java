package com.forestsoftware.sendmeerrandserverjava.repository;

import com.forestsoftware.sendmeerrandserverjava.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmailOrPhoneNumber(String email, String phone);
    User findUserByEmail(String email);
    Optional<User>findByEmailOrUsernameOrPhoneNumber(String email,String username, String phone);
    Optional<User>findByPhoneNumber(String phone);


}
