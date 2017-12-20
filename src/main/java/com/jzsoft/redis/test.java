package com.jzsoft.redis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class test {
	private static ShardedJedisPool shardedPool;
	private static JedisPool pool1;
	private static JedisPool pool2;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
           Jedis jedis = new Jedis("127.0.0.1",6379);
           System.out.println(jedis.ping());
	}
	@Test
    public void testJedisPool() {
        //创建Spring 的 IOC 容器对象
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext-redis.xml");
        ShardedJedis resource = shardedPool.getResource();
        Jedis jedis = pool1.getResource();
//        JedisPool pool = (JedisPool) ctx.getBean("jedisPool");
//        JedisPool pool2 = (JedisPool) ctx.getBean("jedisPool2");
        try {

            jedis.set("name", "testname");
            String name = jedis.get("name");
            System.out.println(name);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (jedis != null) {
                // 关闭连接
                jedis.close();
            }
        }
    }
}
