package com.cn.rating.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RatingService {

    private final Map<String, Double> ratingMap = new HashMap<>();

    public Double getRatingById(String id) {

        return ratingMap.get(id);
    }

    public void addRating(Map<String, Double> hotelRatingMap) {
        ratingMap.putAll(hotelRatingMap);
    }

    public Map<String, Double> getAllRating() {
        return ratingMap;
    }

    public void updateRating(Map<String, Double> hotelRatingMap) {
        ratingMap.putAll(hotelRatingMap);
    }

	public void deleteRating(String id) {
		
		ratingMap.remove(id);
	}
}
