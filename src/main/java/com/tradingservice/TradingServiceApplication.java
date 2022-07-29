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
		/*
		 * BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); for (int i = 0;
		 * i < 5; i++) { // "123456" - plain text - user input from user interface
		 * String passwd = encoder.encode("welcome");
		 * 
		 * // passwd - password from database System.out.println(passwd); // print hash
		 * 
		 * // true for all 5 iteration System.out.println(encoder.matches("welcome",
		 * passwd)); }
		 */
		SpringApplication.run(TradingServiceApplication.class, args);
		
	}

}
