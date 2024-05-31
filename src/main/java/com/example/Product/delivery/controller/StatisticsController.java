package com.example.Product.delivery.controller;


import com.example.Product.delivery.dto.StatisticDTO;
import com.example.Product.delivery.mapper.StatisticMapper;
import com.example.Product.delivery.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticService;

    @GetMapping
    public ResponseEntity<List<StatisticDTO>> getAllStatistics() {
        List<StatisticDTO> statisticDTOs = statisticService.getAllStatistics().stream()
                .map(StatisticMapper::toStatisticDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(statisticDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatisticDTO> getStatisticById(@PathVariable Long id) {
        Optional<StatisticDTO> statistic = statisticService.getStatisticById(id);
        if (statistic.isPresent()) {
            return ResponseEntity.ok(statistic.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<StatisticDTO> createStatistic(@RequestBody StatisticDTO statisticDTO) {
        StatisticDTO createdStatistic = statisticService.createStatistic(statisticDTO);
        return ResponseEntity.ok(createdStatistic);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatisticDTO> updateStatistic(@PathVariable Long id, @RequestBody StatisticDTO statisticDTO) {
        StatisticDTO updatedStatistic = statisticService.updateStatistic(id, statisticDTO);
        return ResponseEntity.ok(updatedStatistic);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatistic(@PathVariable Long id) {
        statisticService.deleteStatistic(id);
        return ResponseEntity.noContent().build();
    }
}

