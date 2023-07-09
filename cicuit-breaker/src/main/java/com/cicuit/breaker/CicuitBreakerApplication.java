package com.cicuit.breaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class CicuitBreakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CicuitBreakerApplication.class, args);
	}

}
