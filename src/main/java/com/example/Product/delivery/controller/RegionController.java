package com.example.Product.delivery.controller;

import com.example.Product.delivery.dto.RegionDTO;
import com.example.Product.delivery.mapper.RegionMapper;
import com.example.Product.delivery.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @PreAuthorize("hasAuthority('REGION_READ')")
    @GetMapping("/list")
    public ResponseEntity<List<RegionDTO>> getAllRegions() {
        List<RegionDTO> regionDTOs = regionService.getAllRegions().stream()
                .map(RegionMapper::toRegionDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(regionDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> getRegionById(@PathVariable Long id) {
        Optional<RegionDTO> region = regionService.getRegionById(id);
        if (region.isPresent()) {
            return ResponseEntity.ok(region.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAuthority('REGION_CREATE')")
    @PostMapping("/add")
    public ResponseEntity<RegionDTO> createRegion(@RequestBody RegionDTO regionDTO) {
        RegionDTO createdRegion = regionService.createRegion(regionDTO);
        return ResponseEntity.ok(createdRegion);
    }

    @PreAuthorize("hasAuthority('REGION_EDIT')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<RegionDTO> updateRegion(@PathVariable Long id, @RequestBody RegionDTO regionDTO) {
        RegionDTO updatedRegion = regionService.updateRegion(id, regionDTO);
        return ResponseEntity.ok(updatedRegion);
    }

    @PreAuthorize("hasAuthority('REGION_DELETE')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable Long id) {
        regionService.deleteRegion(id);
        return ResponseEntity.noContent().build();
    }
}

