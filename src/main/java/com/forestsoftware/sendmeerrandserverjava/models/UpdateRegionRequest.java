package com.forestsoftware.sendmeerrandserverjava.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateRegionRequest {
    @NotNull
    private Long id;
    @NotNull
    private String title;
    private boolean available;
}
