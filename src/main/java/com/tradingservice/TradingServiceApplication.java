package com.tradingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.tradingservice.webservice.AlphaVantageAPIRestClient;

@SpringBootApplication
@EnableFeignClients(
	    basePackageClasses = {	
	    		AlphaVantageAPIRestClient.class
	    		
	    })
public class TradingServiceApplication {

	public static void main(String[] args) {		
		SpringApplication.run(TradingServiceApplication.class, args);
		
	}

}
