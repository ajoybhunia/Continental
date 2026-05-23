package org.tw.searchservice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.tw.searchservice.dto.HotelDto;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepository {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public void pushToQueue(String key, String value) {
		redisTemplate.opsForList().leftPush(key, value);
	}

	public Optional<List<HotelDto>> getHotelsFromCache(String city) {
		String cityJson = redisTemplate.opsForValue().get(city);
		if (cityJson == null) {
			return Optional.empty();
		}
		ObjectMapper mapper = new ObjectMapper();
		List<HotelDto> hotelDtos = mapper.readValue(cityJson, new TypeReference<List<HotelDto>>() {
		});
		return Optional.of(hotelDtos);
	}

	public void storeHotelsInCache(String city,List<HotelDto> hotelsByCity) {
		String json =  new ObjectMapper().writeValueAsString(hotelsByCity);
		redisTemplate.opsForValue().set(city, json, 30, TimeUnit.SECONDS);
	}
}
