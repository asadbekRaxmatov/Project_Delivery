package com.example.Product.delivery.service;

import com.example.Product.delivery.domain.Statistic;
import com.example.Product.delivery.dto.StatisticDTO;
import com.example.Product.delivery.mapper.StatisticMapper;
import com.example.Product.delivery.repository.StatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticsService {

    private StatisticsRepository statisticRepository;

    public List<Statistic> getAllStatistics() {
        return statisticRepository.findAll();
    }

    public Optional<StatisticDTO> getStatisticById(Long id) {
        Optional<Statistic> statisticOptional = statisticRepository.findById(id);
        return statisticOptional.map(StatisticMapper::toStatisticDTO);
    }

    public StatisticDTO createStatistic(StatisticDTO statisticDTO) {
        Statistic statistic = StatisticMapper.toStatistic(statisticDTO);
        Statistic savedStatistic = statisticRepository.save(statistic);
        return StatisticMapper.toStatisticDTO(savedStatistic);
    }

    public StatisticDTO updateStatistic(Long id, StatisticDTO updatedStatisticDTO) {
        if (statisticRepository.existsById(id)) {
            Statistic updatedStatistic = StatisticMapper.toStatistic(updatedStatisticDTO);
            updatedStatistic.setId(id);
            Statistic savedStatistic = statisticRepository.save(updatedStatistic);
            return StatisticMapper.toStatisticDTO(savedStatistic);
        } else {
            throw new RuntimeException("Statistic not found with id: " + id);
        }
    }


    public void deleteStatistic(Long id) {
        statisticRepository.deleteById(id);
    }
}
