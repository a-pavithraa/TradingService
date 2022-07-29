package com.tradingservice.webservice;

import org.springframework.context.annotation.Bean;

import feign.Retryer;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.HashSet;
import java.util.Set;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;


public class TradingServiceFeignConfig {
	
	 @Bean
	  public Retryer retryer() {
	    return new Retryer.Default(100, SECONDS.toMillis(1), 3);
	  }

	  @Bean
	  public RequestInterceptor feignRequestInterceptor() {
		  
	    return t -> t.query("apikey", "${restapi.alpha.apikey}");
	  }

	  @Bean
	  public ErrorDecoder errorDecoder() {
		
	    Set<Integer> retryableStatusCodes = new HashSet<>();
	    retryableStatusCodes.add(500);
	    retryableStatusCodes.add(503);
	    
	    return new FeignErrorDecoder(retryableStatusCodes);
	  }
	  
	 

}
