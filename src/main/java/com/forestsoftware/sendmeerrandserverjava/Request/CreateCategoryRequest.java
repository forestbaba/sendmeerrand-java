package com.forestsoftware.sendmeerrandserverjava.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryRequest {
    private String title;
    private String image;
    private boolean available;
}
