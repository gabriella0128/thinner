package com.nns.thinner.service;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisTemplateService {

	private final RedisTemplate<String, Object> redisTemplate;

	public void setData(String key, Object value, long refreshTokenValidMilliSeconds) {
		redisTemplate.opsForValue().set(key, value, Duration.ofMillis(refreshTokenValidMilliSeconds));
	}

	public Object getData(String key) {
		return redisTemplate.opsForValue().get(key);
	}

	public Boolean exist(String key) {
		return redisTemplate.hasKey(key);
	}

	public void deleteData(String key) {
		redisTemplate.delete(key);
	}

}
