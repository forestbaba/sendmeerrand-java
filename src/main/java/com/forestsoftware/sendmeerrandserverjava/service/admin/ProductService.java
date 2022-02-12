package com.forestsoftware.sendmeerrandserverjava.service.seller;

import com.forestsoftware.sendmeerrandserverjava.Request.CreateProductImageRequest;
import com.forestsoftware.sendmeerrandserverjava.Request.CreateSellerProduct;
import com.forestsoftware.sendmeerrandserverjava.models.ProductImages;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    ResponseEntity createSellerProduct(CreateSellerProduct createSellerProduct);

    ResponseEntity uploadProductImage(long productImages, MultipartFile multipartFile);

    ResponseEntity getProduct(long id);
}
