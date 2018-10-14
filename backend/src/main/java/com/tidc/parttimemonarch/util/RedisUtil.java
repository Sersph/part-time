package com.tidc.parttimemonarch.util;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisUtil {

    public static void set(String key, String value, long time ,StringRedisTemplate stringRedisTemplate){
        stringRedisTemplate.opsForValue().set(key, value);

        stringRedisTemplate.expire(key, time , TimeUnit.SECONDS);
    }


    public static Object getValueByKey(String key, StringRedisTemplate stringRedisTemplate){
        return stringRedisTemplate.opsForValue().get(key);
    }



}
