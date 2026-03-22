package com.misis.archapp.user.service.cache;

import com.misis.archapp.user.dto.UserDTO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserCacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    public UserCacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static final String PREFIX = "user:";
    private static final Duration TTL = Duration.ofSeconds(60);

    public Optional<UserDTO> getFromCache(UUID id) {
        return Optional.ofNullable((UserDTO) redisTemplate.opsForValue().get(PREFIX + id));
    }

    public void saveToCache(UserDTO user) {
        redisTemplate.opsForValue().set(PREFIX + user.id(), user, TTL);
    }

    public void deleteFromCache(UUID id) {
        redisTemplate.delete(PREFIX + id);
    }
}