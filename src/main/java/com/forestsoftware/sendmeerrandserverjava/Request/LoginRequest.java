package com.forestsoftware.sendmeerrandserverjava.Request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
