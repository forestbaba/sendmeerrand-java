package com.forestsoftware.sendmeerrandserverjava.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank(message = "password field is required")
    @Size(min = 4, max = 20)
    private String password;

    @NotBlank
    @Size(min = 3)
    private String username;

    @NotBlank
    @Size(min = 3)
    private String phone;

    private String role;

}
