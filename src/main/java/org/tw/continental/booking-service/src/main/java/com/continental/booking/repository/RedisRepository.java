package com.continental.booking.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepository {
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public void pushToQueue(String key, String value) {
		redisTemplate.opsForList().leftPush(key, value);
	}
}
