package com.tradingservice.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tradingservice.entity.SymbolDailyPerformance;
import com.tradingservice.model.DailySymbolPerformanceResponse;
import com.tradingservice.model.SymbolPerformanceDTO;
import com.tradingservice.service.DailyPerformanceService;
import com.tradingservice.webservice.AlphaVantageAPIRestClient;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/ingestion")
public class DataIngestionController {
	@Autowired
	private DailyPerformanceService dailyPerformanceService;
	@Autowired
	private AlphaVantageAPIRestClient restClient;
	private static final Logger logger = LoggerFactory.getLogger(DataIngestionController.class);

	@PostMapping("/dailyPerformance")
	@ResponseStatus(HttpStatus.CREATED)
	public String getResp(@RequestParam String symbol){			
		
		DailySymbolPerformanceResponse dailyPerformance=restClient.getDailyPerformance(symbol,"TIME_SERIES_DAILY");
		Map<String, SymbolPerformanceDTO> response =dailyPerformance.getDailyResponse();
		if(response!=null) {
		List<SymbolDailyPerformance> dailyPerformanceList=	response.entrySet().stream().map(entry->{
				SymbolDailyPerformance entity = new SymbolDailyPerformance();
				entity.setDay(entry.getKey());
				SymbolPerformanceDTO dto = entry.getValue();
				entity.setClose(dto.getClose());
				entity.setHigh(dto.getHigh());
				entity.setLow(dto.getLow());
				entity.setVolume(dto.getVolume());
				entity.setSymbol(symbol);
				return entity;
				
			}).collect(Collectors.toList());
			dailyPerformanceService.saveDaiyPerformce(dailyPerformanceList);
		}
		logger.debug("response:{}",dailyPerformance);
		 	
	        return  "Data Inserted";
	    }

}
