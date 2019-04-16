package com.example.demo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/rest/Stock")
public class StockResource {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable("username") final String username) {

		// List<String> quotes=
		// restTemplate.getForObject("http://localhost:8300/rest/db/"+username,List.class);

		ResponseEntity<List<String>> quotesResponse = restTemplate.exchange("http://db-service/rest/db/" + username,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
				});
		List<String> quotes = quotesResponse.getBody();
		return quotes;
	}

}
