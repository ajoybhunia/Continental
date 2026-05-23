package org.tw.searchservice.service;

import org.springframework.stereotype.Service;
import org.tw.searchservice.dto.HotelDto;
import org.tw.searchservice.repository.HotelRepository;
import org.tw.searchservice.repository.RedisRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SearchService {
	private final HotelRepository hotelRepository;
	private final RedisRepository redisRepository;

	public SearchService(HotelRepository hotelRepository, RedisRepository redisRepository) {
		this.hotelRepository = hotelRepository;
		this.redisRepository = redisRepository;
	}

	public List<HotelDto> filterHotelsByCity(String city) {
		Optional<List<HotelDto>> hotels = redisRepository.getHotelsFromCache(city);
		if (hotels.isPresent()) {
			return hotels.get();
		}
		List<HotelDto> hotelsByCity = hotelRepository.findHotelsByCity(city);
		redisRepository.storeHotelsInCache(city, hotelsByCity);
		return hotelsByCity;
	}
}
