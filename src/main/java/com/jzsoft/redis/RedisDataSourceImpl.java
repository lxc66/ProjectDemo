package com.jzsoft.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Slf4j
@Repository("redisDataSource")
public class RedisDataSourceImpl implements RedisDataSource{

	    @Autowired
	    private ShardedJedisPool   shardedJedisPool;

	    public ShardedJedis getRedisClient() {
	        try {
	            ShardedJedis shardJedis = shardedJedisPool.getResource();
	            return shardJedis;
	        } catch (Exception e) {
	            log.error("getRedisClent error", e);
	        }
	        return null;
	    }

	    public void returnResource(ShardedJedis shardedJedis) {
	        shardedJedisPool.close();
	        //shardedJedisPool.returnResource(shardedJedis);
	    }

	    public void returnResource(ShardedJedis shardedJedis, boolean broken) {
	        if (broken) {
	            shardedJedisPool.close();
	            //shardedJedisPool.returnBrokenResource(shardedJedis);
	        } else {
	            shardedJedisPool.close();
	            //shardedJedisPool.returnResource(shardedJedis);
	        }
	    }

}
