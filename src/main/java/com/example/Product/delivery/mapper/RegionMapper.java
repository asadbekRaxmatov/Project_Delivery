package com.example.Product.delivery.mapper;


import com.example.Product.delivery.domain.Region;
import com.example.Product.delivery.dto.RegionDTO;

public class RegionMapper {

    public static RegionDTO toRegionDTO(Region region) {
        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setId(region.getId());
        regionDTO.setName(region.getName());

        return regionDTO;
    }

    public static Region toRegion(RegionDTO regionDTO) {
        Region region = new Region();
        region.setId(regionDTO.getId());
        region.setName(regionDTO.getName());

        return region;
    }
}

