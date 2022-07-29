package com.tradingservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tradingservice.entity.SymbolDailyPerformance;

@Repository
public interface SymbolDailyPerformanceRepository extends JpaRepository<SymbolDailyPerformance, Long> {
	
	public List<SymbolDailyPerformance> findBySymbolEquals(String symbol);
	

}
