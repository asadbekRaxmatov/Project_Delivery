package com.example.Product.delivery.mapper;

import com.example.Product.delivery.domain.Statistic;
import com.example.Product.delivery.dto.StatisticDTO;

public class StatisticMapper {

    public static StatisticDTO toStatisticDTO(Statistic statistic) {
        StatisticDTO statisticDTO = new StatisticDTO();
        statisticDTO.setId(statistic.getId());
        statisticDTO.setDate(statistic.getDate());
        statisticDTO.setValue(statistic.getValue());

        return statisticDTO;
    }

    public static Statistic toStatistic(StatisticDTO statisticDTO) {
        Statistic statistic = new Statistic();
        statistic.setId(statisticDTO.getId());
        statistic.setDate(statisticDTO.getDate());
        statistic.setValue(statisticDTO.getValue());

        return statistic;
    }
}

