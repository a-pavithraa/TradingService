package com.tradingservice.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import com.tradingservice.entity.SymbolDailyPerformance;
import com.tradingservice.model.DailySymbolPerformanceResponse;
import com.tradingservice.model.SymbolPerformanceDTO;
import com.tradingservice.repository.SymbolDailyPerformanceRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DailyPerformanceService {
	private SymbolDailyPerformanceRepository symbolDailyPerformanceRepo;

	@Transactional(readOnly = true)
	public List<SymbolDailyPerformance> getDailyPerformance(String symbol) {
		return symbolDailyPerformanceRepo.findBySymbolEquals(symbol);
	}

	@Transactional
	public void saveDaiyPerformce(DailySymbolPerformanceResponse dailyPerformance, String symbol) {
		Map<String, SymbolPerformanceDTO> response = dailyPerformance.getDailyResponse();
		
			List<SymbolDailyPerformance> dailyPerformanceList = response.entrySet().stream().map(entry -> {
				SymbolDailyPerformance entity = new SymbolDailyPerformance();
				entity.setDay(entry.getKey());
				SymbolPerformanceDTO dto = entry.getValue();
				entity.setClose(dto.getClose());
				entity.setOpen(dto.getOpen());
				entity.setHigh(dto.getHigh());
				entity.setLow(dto.getLow());
				entity.setVolume(dto.getVolume());
				entity.setSymbol(symbol);
				return entity;

			}).collect(Collectors.toList());

			symbolDailyPerformanceRepo.saveAll(dailyPerformanceList);
		
	}

}
