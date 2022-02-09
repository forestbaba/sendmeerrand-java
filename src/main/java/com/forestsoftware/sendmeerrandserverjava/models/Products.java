package com.forestsoftware.sendmeerrandserverjava.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;


    private Double price;
    @Column(name = "discount_price")
    private Double discountPrice;

    @Column(name = "is_deal")
    private boolean isDeal;

    private boolean available;

    @NotBlank
    private Region region;

    @Column(name = "category_id")
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinTable(name = "category",
//    joinColumns = @JoinColumn("category_id"))
    private Long Category;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;





}
