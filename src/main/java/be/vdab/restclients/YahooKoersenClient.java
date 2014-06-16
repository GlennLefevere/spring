package be.vdab.restclients;

import java.math.BigDecimal;
import java.net.URI;

import org.apache.felix.ipojo.annotations.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

@Component
class YahooKoersenClient implements KoersenClient{

	private final URI yahooURL;
	private final RestTemplate restTemplate;
	@Autowired
	YahooKoersenClient(@Value("${yahooKoersenURL}") URI yahooURL,
	RestTemplate restTemplate) {
	this.yahooURL = yahooURL;
	this.restTemplate = restTemplate;
	}
	
	@Override
	public BigDecimal getDollarKoers() {
		// TODO Auto-generated method stub
		return null;
	}

	public URI getYahooURL() {
		return yahooURL;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

}
