package com.example.Product.delivery.repository;

import com.example.Product.delivery.domain.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistic, Long> {
}

