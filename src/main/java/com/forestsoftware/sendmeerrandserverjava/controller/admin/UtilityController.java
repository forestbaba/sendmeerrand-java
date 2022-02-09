package com.forestsoftware.sendmeerrandserverjava.controller.admin;

import com.forestsoftware.sendmeerrandserverjava.Request.CreateBannerRequest;
import com.forestsoftware.sendmeerrandserverjava.Request.CreateCategoryRequest;
import com.forestsoftware.sendmeerrandserverjava.Request.CreateRegionRequest;
import com.forestsoftware.sendmeerrandserverjava.Request.UpdateBannerRequest;
import com.forestsoftware.sendmeerrandserverjava.models.UpdateRegionRequest;
import com.forestsoftware.sendmeerrandserverjava.service.banner.BannerService;
import com.forestsoftware.sendmeerrandserverjava.service.category.CategoryService;
import com.forestsoftware.sendmeerrandserverjava.service.region.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UtilityController {

    @Autowired
    private BannerService bannerService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private RegionService regionService;


    //*********************** BANNER **********************//
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-banner")
    public ResponseEntity createBanner(@Valid @RequestBody CreateBannerRequest createBannerRequest){
        return bannerService.createBanner(createBannerRequest);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/all-banners")
    public ResponseEntity getAllBanners(){
        return bannerService.getAllBanners();
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/banner/{id}")
    public ResponseEntity getBanner(@Valid @PathVariable Long id){
        return bannerService.getOneBanner(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update-banner/{id}")
    public ResponseEntity updateBanner(@Valid @PathVariable Long id, @RequestBody UpdateBannerRequest updateBannerRequest){
        return bannerService.updateBanner(id, updateBannerRequest);
    }

    //*********************** Category **********************//

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-category")
    public ResponseEntity createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest){
        return categoryService.createCategory(createCategoryRequest);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/all-categories")
    public ResponseEntity getAllCategories(){
        return categoryService.fetchAllCategories();
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/available-categories")
    public ResponseEntity getAvailableCategory(){
        return categoryService.fetchAvailableCategories();
    }


    //*********************** Region ***********************//
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-region")
    public ResponseEntity createRegion(@Valid @RequestBody CreateRegionRequest createRegionRequest){
        return regionService.createRegion(createRegionRequest);

    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/get-all-regions")
    public ResponseEntity getAllRegions(){
        return regionService.getAllRegions();
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/get-region/{id}")
    public ResponseEntity getRegionById(@PathVariable Long id){
        return regionService.getRegionById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update-region")
    public ResponseEntity updateRegion(@Valid @RequestBody UpdateRegionRequest updateRegionRequest){
        return regionService.updateRegion(updateRegionRequest);
    }

}
