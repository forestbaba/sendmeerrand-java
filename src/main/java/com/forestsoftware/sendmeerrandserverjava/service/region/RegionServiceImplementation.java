package com.forestsoftware.sendmeerrandserverjava.service.region;


import com.forestsoftware.sendmeerrandserverjava.Request.CreateRegionRequest;
import com.forestsoftware.sendmeerrandserverjava.Response.ApiResponse;
import com.forestsoftware.sendmeerrandserverjava.models.Region;
import com.forestsoftware.sendmeerrandserverjava.models.UpdateRegionRequest;
import com.forestsoftware.sendmeerrandserverjava.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegionServiceImplementation implements RegionService{
    @Autowired
    private RegionRepository regionRepository;

    @Override
    public ResponseEntity createRegion(CreateRegionRequest createRegionRequest) {
        Region region = regionRepository.findByTitle(createRegionRequest.getTitle());
        if(region != null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new ApiResponse(false,String.format("Region with title %s already exist", createRegionRequest.getTitle())));
        }
        Region region1 = new Region();
        region1.setTitle(createRegionRequest.getTitle());
        region1.setAvailable(createRegionRequest.isAvailable());
        regionRepository.save(region1);
       return  ResponseEntity.status(HttpStatus.CREATED).body( new ApiResponse(true, "Region created", region1));
    }

    @Override
    public ResponseEntity getAllRegions() {
        return  ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,"Regions fetched", regionRepository.findAll()));
    }

    @Override
    public ResponseEntity getRegionById(Long id) {
        Optional region = regionRepository.findById(id);
        if(!region.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(true,String.format("Region with %d not found", id)));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true,"Region fetched", region));
    }

    @Override
    public ResponseEntity updateRegion(UpdateRegionRequest updateRegionRequest) {
        Region region = regionRepository.findRegionById(updateRegionRequest.getId());
        if(region == null){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(true,String.format("Region with %d not found", updateRegionRequest.getId())));
        }
        region.setTitle(updateRegionRequest.getTitle());
        region.setAvailable(updateRegionRequest.isAvailable());
        regionRepository.save(region);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Region update success", region));
    }
}
