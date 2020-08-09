package com.nazarick.note.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value, Integer timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    public void setIfAbsent(String key, Object value, Integer timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().setIfAbsent(key, value, timeout, timeUnit);
    }

    public <T> T get(String key, Class<T> clazz) {
        Object valObj = redisTemplate.opsForValue().get(key);
        return JSONObject.parseObject(JSON.toJSONString(valObj), clazz);
    }

    public boolean exists(String key) {
        Boolean result = redisTemplate.hasKey(key);
        return result != null && result;
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void deleteNamespace(String namespace) {
        Set<String> set = redisTemplate.keys(namespace + ":*");
        if (set != null && set.size() > 0) {
            redisTemplate.delete(set);
        }
    }
}
