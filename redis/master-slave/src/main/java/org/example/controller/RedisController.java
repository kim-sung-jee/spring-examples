package org.example.controller;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class RedisController {
    private final StringRedisTemplate template;
    private final String KEY = "sungjee";

    public RedisController(StringRedisTemplate stringRedisTemplate) {
        this.template = stringRedisTemplate;
    }

    @GetMapping("/{name}")
    public void addToSet(@PathVariable String name) {
        this.template.opsForValue().set(KEY, name);
    }

    @GetMapping("/name")
    public String getKeyValues(){
        List<RedisClientInfo> clientList = template.getClientList();
        System.out.println(clientList.size());
        for (RedisClientInfo redisClientInfo : clientList) {
            System.out.println(redisClientInfo);
        }
        return this.template.opsForValue().get(KEY);
    }
}
