package com.cicuit.breaker;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rabbitmq.client.Command;



@RestController
public class CircuitBreakerController {
   
	@GetMapping("/data")
	@HystrixCommand(fallbackMethod = "fail",
	commandProperties = {
			@HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "5"),
	        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000")
	}
			)
	public String wish() {
		System.out.println("wish method");
		//System.out.println(10/0);
		String msg="Welcome To microService CircuitBreaker..";
		
		return msg;
	}
	
	public String fail() {
		System.out.println("fail Method");
		String msgs="microService CircuitBreaker Example working fine";
		return msgs;
	}
	
	
}
