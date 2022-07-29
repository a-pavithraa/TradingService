package com.tradingservice.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailySymbolPerformanceResponse {
	@JsonProperty("Time Series (Daily)")
	private Map<String,SymbolPerformanceDTO> dailyResponse;

}
