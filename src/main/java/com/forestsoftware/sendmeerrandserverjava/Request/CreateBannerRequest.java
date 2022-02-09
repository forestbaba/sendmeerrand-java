package com.forestsoftware.sendmeerrandserverjava.Request;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateBannerRequest {
    @NotBlank
    private String title;
    private String image;
    private Boolean active;
}
