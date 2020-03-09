package com.example.RedisSample.controller;

import com.example.RedisSample.bean.RedisData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(method = RequestMethod.PUT)
    public void put(@RequestBody RedisData redisData) {
        // String
        redisTemplate.opsForValue().set("hoge:string", redisData.getString());

        // List
        redisTemplate.delete("hoge:list");
        redisTemplate.opsForList().rightPushAll("hoge:list", redisData.getList().toArray(new String[0]));

        // Map
        redisTemplate.delete("hoge:map");
        redisTemplate.opsForHash().putAll("hoge:map", redisData.getMap());
    }

    @RequestMapping(method = RequestMethod.GET)
    public RedisData get() {
        RedisData redisData = new RedisData();

        // String
        redisData.setString(redisTemplate.opsForValue().get("hoge:string"));

        // List
        redisData.setList(redisTemplate.opsForList().range("hoge:list",0, -1));

        // Map
        redisData.setMap(redisTemplate.<String, String>opsForHash().entries("hoge:map"));

        return redisData;
    }

}
