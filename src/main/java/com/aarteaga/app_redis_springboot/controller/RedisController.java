package com.aarteaga.app_redis_springboot.controller;


import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

    private final ReactiveValueOperations<String, String> valueOps;

    public RedisController(ReactiveRedisTemplate<String, String> template) {
        this.valueOps = template.opsForValue();
    }

    @PostMapping("/{key}")
    public Mono<Boolean> setValue(@PathVariable String key, @RequestBody String value) {
        return valueOps.set(key, value);
    }

    @GetMapping("/{key}")
    public Mono<String> getValue(@PathVariable String key) {
        return valueOps.get(key);
    }
}
