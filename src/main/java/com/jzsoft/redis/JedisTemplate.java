package com.jzsoft.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.ShardedJedis;

@Repository("jedisTemplate")
@Slf4j
public class JedisTemplate {
	   
	    @Autowired
	    private RedisDataSource   redisDataSource;

	    public void disconnect() {
	        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
	        shardedJedis.disconnect();
	    }

	    /**
	     * 设置单个值
	     * 
	     * @param key
	     * @param value
	     * @return
	     */
	    public String set(String key, String value) {
	        String result = null;

	        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
	        if (shardedJedis == null) {
	            return result;
	        }
	        boolean broken = false;
	        try {
	            result = shardedJedis.set(key, value);
	        } catch (Exception e) {
	            e.printStackTrace();
	            log.error(e.getMessage(), e);
	            broken = true;
	        } finally {
	            redisDataSource.returnResource(shardedJedis, broken);
	        }
	        return result;
	    }

	    /**
	     * 获取单个值
	     * 
	     * @param key
	     * @return
	     */
	    public String get(String key) {
	        String result = null;
	        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
	        if (shardedJedis == null) {
	            return result;
	        }

	        boolean broken = false;
	        try {
	            result = shardedJedis.get(key);

	        } catch (Exception e) {
	            log.error(e.getMessage(), e);
	            broken = true;
	        } finally {
	            redisDataSource.returnResource(shardedJedis, broken);
	        }
	        return result;
	    }
}
