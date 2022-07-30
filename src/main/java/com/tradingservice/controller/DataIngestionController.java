package com.tradingservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tradingservice.model.DailySymbolPerformanceResponse;
import com.tradingservice.service.DailyPerformanceService;
import com.tradingservice.webservice.AlphaVantageAPIRestClient;

import lombok.AllArgsConstructor;

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
	public void insertDailyPerformanceData(@RequestParam String symbol) {

		DailySymbolPerformanceResponse dailyPerformance = restClient.getDailyPerformance(symbol, "TIME_SERIES_DAILY");
		if (dailyPerformance != null) {
			dailyPerformanceService.saveDaiyPerformce(dailyPerformance, symbol);
		}
		logger.debug("response:{}", dailyPerformance);

		
	}

}
