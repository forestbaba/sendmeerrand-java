package com.forestsoftware.sendmeerrandserverjava.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBannerRequest {
    private String title;
    private String image;
    private Boolean active;
}
