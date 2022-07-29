package com.tradingservice.webservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tradingservice.model.DailySymbolPerformanceResponse;

@FeignClient(
	    name = "alphaVantage",
	    url = "${restapi.alpha.url}",
	    configuration = TradingServiceFeignConfig.class)
	public interface AlphaVantageAPIRestClient {
	  @RequestMapping(
	      method = RequestMethod.GET,
	      value = "query",
	      consumes = "application/json")
	  DailySymbolPerformanceResponse getDailyPerformance(@RequestParam(value="symbol")String symbol, @RequestParam(value="function")String function);
	}
