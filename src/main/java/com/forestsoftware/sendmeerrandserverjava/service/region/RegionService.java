package com.forestsoftware.sendmeerrandserverjava.service.region;

import com.forestsoftware.sendmeerrandserverjava.Request.CreateRegionRequest;
import com.forestsoftware.sendmeerrandserverjava.models.UpdateRegionRequest;
import org.springframework.http.ResponseEntity;

public interface RegionService {
    ResponseEntity createRegion(CreateRegionRequest createRegionRequest);

    ResponseEntity getAllRegions();

    ResponseEntity getRegionById(Long id);

    ResponseEntity updateRegion(UpdateRegionRequest updateRegionRequest);
}
