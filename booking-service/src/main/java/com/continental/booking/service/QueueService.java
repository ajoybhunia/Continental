package com.continental.booking.service;

import com.continental.booking.model.Booking;
import com.continental.booking.repository.RedisRepository;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class QueueService {
	private final RedisRepository redisRepository;

	public QueueService(RedisRepository redisRepository) {
		this.redisRepository = redisRepository;
	}

	public void addJob(Booking booking) {
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(booking);
		redisRepository.pushToQueue("pdf-jobs", json);
	}
}
