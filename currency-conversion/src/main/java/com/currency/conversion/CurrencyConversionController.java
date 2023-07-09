package com.currency.conversion;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CurrencyConversionController {
	String apiUrl = "http://localhost:8001/currency-exchange/from/{from}/to/{to}";
	RestTemplate restTemplate = new RestTemplate();

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")

	@CircuitBreaker(name = "currency-conversion", fallbackMethod = "errorCurrency")

	public CurrencyConversion totalConversion(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		//System.out.println(10 / 0);
		HashMap<String, String> uriVariable = new HashMap<>();
		uriVariable.put("from", from);
		uriVariable.put("to", to);

		CurrencyConversion currencyConversion = restTemplate.getForObject(apiUrl, CurrencyConversion.class,
				uriVariable);
		currencyConversion.setQuantity(quantity);
		currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));

		return currencyConversion;

	}

	public CurrencyConversion errorCurrency(Exception e) {

		return new CurrencyConversion(11l, "usd", "inr", null, null, null);
	}

}
