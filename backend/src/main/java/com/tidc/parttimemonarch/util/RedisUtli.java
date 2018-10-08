package com.tidc.parttimemonarch.util;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisUtli {
    @Autowired
    private static StringRedisTemplate stringRedisTemplate;
    @Autowired
    private static RedisTemplate redisTemplate;


    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间(秒)
     * @return boolean
     */
    public static boolean expire(String key, long time){
        try{
            if (time > 0){
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            LoggerFactory.getLogger(RedisUtli.class).info("莫名其妙的错误");
            return false;
        }
    }


    public static long getExpire(String key){
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }



    public static boolean hasKey(String key){
        try{
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            LoggerFactory.getLogger(RedisUtli.class).info("莫名其妙的错误");
            return false;
        }
    }



    public static Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }


    public static boolean set(String key, Object value){
        try{
            redisTemplate.opsForValue().set(key, value);
            System.out.println(key + value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            LoggerFactory.getLogger(RedisUtli.class).info("莫名其妙的错误");
            return false;
        }
    }

    public static boolean set(String key, Object value, long time){
        try{
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time);
                return true;
            }

            set(key, value);
            return true;
        }catch (Exception e){
            LoggerFactory.getLogger(RedisUtli.class).info("莫名其妙的错误");
            return false;
        }
    }


}
