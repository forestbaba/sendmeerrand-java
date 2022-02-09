package com.forestsoftware.sendmeerrandserverjava.repository;

import com.forestsoftware.sendmeerrandserverjava.models.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository  extends JpaRepository<Banner, Long> {
    Banner findBannerById(Long id);
}
