package com.forestsoftware.sendmeerrandserverjava.models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePassword {

    private String oldPassword;
    private String newPassword;
}
