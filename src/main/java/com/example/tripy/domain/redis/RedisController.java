package com.example.tripy.domain.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    private final RedisService redisService;

    @Autowired
    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    // Redis에 데이터 저장
    @GetMapping("/redis/set")
    public void setRedisData(@RequestParam String key, @RequestParam String value) {
        redisService.setValue(key, value);
    }

    // Redis에서 데이터 검색
    @GetMapping("/redis/get/{key}")
    public String getRedisData(@PathVariable String key) {
        return redisService.getValue(key);
    }

}