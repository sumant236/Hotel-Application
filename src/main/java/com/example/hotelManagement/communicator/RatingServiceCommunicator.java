package com.example.hotelManagement.communicator;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.hotelManagement.exceptions.HttpRatingServiceNotFound;

@Service
public class RatingServiceCommunicator {

	
	private final RestTemplate restTemplate;
	
	@Autowired
	public RatingServiceCommunicator(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate=restTemplateBuilder.build();
	}
	
	public double getRating(String id)
	{
		String url ="http://localhost:8081/rating/id/";

//		 Double ratingResponse = restTemplate.getForObject(url+id, Double.class);
//		return ratingResponse;
		//ResponseEntity<Double> response= restTemplate.getForEntity(url+id, Double.class);
		ResponseEntity<Double> response= restTemplate.exchange(url+id, HttpMethod.GET, null,Double.class);
		return response.getBody();
	}

	public void addRating(Map<String, Double> ratingsMap) {
		
		String url ="http://localhost:8081/rating/add";
		
		//restTemplate.postForObject(url, ratingsMap, Object.class);
		HttpEntity<Map<String, Double>> requestEntity = new HttpEntity<>(ratingsMap);
		
		restTemplate.exchange(url,HttpMethod.POST,requestEntity,Object.class);
	}
	
	public void updateRating(Map<String, Double> ratingsMap)
	{
		String url ="http://localhost:8081/rating/update";
		
		HttpEntity<Map<String, Double>> requestEntity = new HttpEntity<>(ratingsMap);
		
		restTemplate.exchange(url,HttpMethod.PUT,requestEntity,Object.class);
	}
	
	public void deleteRating(String id)
	{
		String url ="http://localhost:8081/rating/remove/id/";
		
		try {
			restTemplate.exchange(url+id,HttpMethod.DELETE,null,Object.class);
		}
		catch(HttpClientErrorException e)
		{
			throw new HttpRatingServiceNotFound(HttpStatusCode.valueOf(HttpStatus.NOT_FOUND.value()));
		}
		

	}
	
}
