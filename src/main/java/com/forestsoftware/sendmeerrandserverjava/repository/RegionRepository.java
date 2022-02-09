package com.forestsoftware.sendmeerrandserverjava.repository;

import com.forestsoftware.sendmeerrandserverjava.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region,Long> {

    Region findByTitle(String title);
    Region findRegionById(Long id);
}
