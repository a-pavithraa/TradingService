package com.tradingservice.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tradingservice.entity.SymbolDailyPerformance;
import com.tradingservice.repository.SymbolDailyPerformanceRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DailyPerformanceService {
	private SymbolDailyPerformanceRepository symbolDailyPerformanceRepo;
	
	 @Transactional(readOnly = true)
	 public List<SymbolDailyPerformance> getDailyPerformance(String symbol){
		 return symbolDailyPerformanceRepo.findBySymbolEquals(symbol);
	 }
	 @Transactional
	 public void saveDaiyPerformce(List<SymbolDailyPerformance> symbolDailyPerformance) {
		 symbolDailyPerformanceRepo.saveAll(symbolDailyPerformance);
	 }

}
