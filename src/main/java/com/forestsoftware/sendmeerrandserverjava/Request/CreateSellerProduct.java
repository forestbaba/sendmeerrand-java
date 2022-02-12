package com.forestsoftware.sendmeerrandserverjava.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateSellerProduct {
    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private double price;
    private double discountPrice;
    @NotBlank
    private Long region;
    @NotBlank
    private Long category;
    @NotBlank
    private Long sellerId;
    private boolean isAvailable;
    private boolean isDeal;
}
