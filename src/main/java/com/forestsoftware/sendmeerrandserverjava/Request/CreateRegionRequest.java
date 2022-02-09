package com.forestsoftware.sendmeerrandserverjava.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRegionRequest {
    private String title;
    private boolean available;
}
