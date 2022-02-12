package com.forestsoftware.sendmeerrandserverjava.service.seller;

import com.forestsoftware.sendmeerrandserverjava.Exceptions.AppException;
import com.forestsoftware.sendmeerrandserverjava.Request.CreateProductImageRequest;
import com.forestsoftware.sendmeerrandserverjava.Request.CreateSellerProduct;
import com.forestsoftware.sendmeerrandserverjava.Response.ApiResponse;
import com.forestsoftware.sendmeerrandserverjava.models.*;
import com.forestsoftware.sendmeerrandserverjava.repository.*;
import com.forestsoftware.sendmeerrandserverjava.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImplementation implements ProductService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public ResponseEntity createSellerProduct(CreateSellerProduct createSellerProduct) {
        Objects.requireNonNull(createSellerProduct.getPrice());

        Category category = categoryRepository.findById(createSellerProduct.getCategory()).orElseThrow(()-> new AppException("Category not found"));
        Region region = regionRepository.findById(createSellerProduct.getRegion()).orElseThrow(()-> new AppException("Region not found"));
        User seller = userRepository.findById(createSellerProduct.getSellerId()).orElseThrow(()-> new AppException("Seller not found"));


        Products products = new Products();
        products.setName(createSellerProduct.getName());
        products.setCategory(category);
        products.setAvailable(createSellerProduct.isAvailable());
        products.setDeal(createSellerProduct.isDeal());
        products.setDescription(createSellerProduct.getDescription());
        products.setDiscountPrice(createSellerProduct.getDiscountPrice());
        products.setRegion(region);
        products.setSeller(seller);
        productRepository.save(products);


        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true,"Product created successfully", products));
    }

    @Override
    public ResponseEntity uploadProductImage(long id, MultipartFile multipartFile) {

        Products products = productRepository.findById(id).orElseThrow(() -> new AppException("Product not found"));
        String filename = fileStorageService.storeFile(multipartFile);
        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/file/")
                .path(filename)
                .toUriString();
       ProductImages productImages1 =new ProductImages();
      //  List<ProductImages>li = productImageRepository.findProductImagesByProducts(products);

//       products.setImages(li);


       productImages1.setItemImage(fileUrl);
       productImages1.setProducts(products);

        ProductImages np =  productImageRepository.save(productImages1);
        products.addImages(productImages1);



        productRepository.save(products);

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,"Image uploaded",np));
    }
}
