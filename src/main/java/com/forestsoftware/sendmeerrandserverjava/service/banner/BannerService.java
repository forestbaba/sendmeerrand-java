package com.forestsoftware.sendmeerrandserverjava.service.banner;

import com.forestsoftware.sendmeerrandserverjava.Request.CreateBannerRequest;
import com.forestsoftware.sendmeerrandserverjava.Request.UpdateBannerRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

public interface BannerService {
    ResponseEntity createBanner(CreateBannerRequest createBannerRequest);

    ResponseEntity getAllBanners();

    ResponseEntity getOneBanner(Long id);

    ResponseEntity updateBanner(Long id, UpdateBannerRequest updateBannerRequest);
}
