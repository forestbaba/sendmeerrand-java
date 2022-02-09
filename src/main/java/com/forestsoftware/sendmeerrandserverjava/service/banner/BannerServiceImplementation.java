package com.forestsoftware.sendmeerrandserverjava.service.banner;

import com.forestsoftware.sendmeerrandserverjava.Request.CreateBannerRequest;
import com.forestsoftware.sendmeerrandserverjava.Request.UpdateBannerRequest;
import com.forestsoftware.sendmeerrandserverjava.Response.ApiResponse;
import com.forestsoftware.sendmeerrandserverjava.models.Banner;
import com.forestsoftware.sendmeerrandserverjava.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class BannerServiceImplementation implements BannerService{

    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public ResponseEntity createBanner(CreateBannerRequest createBannerRequest) {
        Banner banner = new Banner();
        banner.setTitle(createBannerRequest.getTitle());
        banner.setImage(createBannerRequest.getImage());
        bannerRepository.save(banner);
        return ResponseEntity.ok(new ApiResponse(true, "Banner created successfully", banner));
    }

    @Override
    public ResponseEntity getAllBanners() {
        return ResponseEntity.ok(new ApiResponse(true, "Banners fetched successfully", bannerRepository.findAll()));
    }

    @Override
    public ResponseEntity getOneBanner(Long id) {
        Optional b = bannerRepository.findById(id);
        if(!b.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body( new ApiResponse(false, String.format("Banner with id %d not found", id),null));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Banner fetched", bannerRepository.findById(id)));
    }

    @Override
    public ResponseEntity updateBanner(Long id, UpdateBannerRequest updateBannerRequest) {
        Banner b = bannerRepository.findBannerById(id);
        if(b == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body( new ApiResponse(false, String.format("Banner with id %d not found", id),null));
        }
        b.setImage(updateBannerRequest.getImage());
        b.setTitle(updateBannerRequest.getTitle());
        b.setActive(updateBannerRequest.getActive());
        bannerRepository.save(b);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse(true, "Banner updated successfully", b));
    }
}
