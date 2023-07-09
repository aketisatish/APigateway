package currencyconversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	CurrencyExchangeRepo exchangeRepo;
	
        @GetMapping("/currency-exchange/from/{from}/to/{to}")
        public CurrencyExchange currencyExchange(@PathVariable String from,@PathVariable String to ) {
        	return  exchangeRepo.findByFromAndTo(from,to);
        }
}
