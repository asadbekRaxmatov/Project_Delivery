package com.example.Product.delivery.service;

import com.example.Product.delivery.domain.Region;
import com.example.Product.delivery.dto.RegionDTO;
import com.example.Product.delivery.mapper.RegionMapper;
import com.example.Product.delivery.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    private RegionRepository regionRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Optional<RegionDTO> getRegionById(Long id) {
        return regionRepository.findById(id)
                .map(RegionMapper::toRegionDTO);
    }

    public RegionDTO createRegion(RegionDTO regionDTO) {
        Region region = RegionMapper.toRegion(regionDTO);
        return RegionMapper.toRegionDTO(regionRepository.save(region));
    }

    public RegionDTO updateRegion(Long id, RegionDTO updatedRegionDTO) {
        if (regionRepository.existsById(id)) {
            Region updatedRegion = RegionMapper.toRegion(updatedRegionDTO);
            updatedRegion.setId(id);
            return RegionMapper.toRegionDTO(regionRepository.save(updatedRegion));
        } else {
            throw new RuntimeException("Region not found with id: " + id);
        }
    }


    public void deleteRegion(Long id) {
        regionRepository.deleteById(id);
    }
}

