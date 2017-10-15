package com.packtpub.yummy.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccessLogRepository {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    public void storeEntry(AccessLogEntry entry){
        redisTemplate.opsForList().rightPush("accessLog:"+entry.getUrl(),entry.getTimestamp());
    }

}
