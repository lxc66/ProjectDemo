package com.jzsoft.platform.core.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.jzsoft.platform.core.spring.SpringContextHolder;
import com.jzsoft.platform.core.web.context.ServletContextMonitor;
import com.jzsoft.platform.util.SerializationUtils;

@Component
public class CacheHelper implements ServletContextMonitor{
	private static RedisTemplate<Serializable, Serializable> redisTemplate; 
	public static void set(final String key , final String value){
		if(redisTemplate==null) return;
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] byte_key = redisTemplate.getStringSerializer().serialize(key);
				byte[] byte_value = redisTemplate.getStringSerializer().serialize(value);
				connection.set(byte_key, byte_value);
				return true;
			}
		});
	}
	
	public static void set(final String key , final Object value){
		if(redisTemplate==null) return;
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] byte_key = redisTemplate.getStringSerializer().serialize(key);
				byte[] byte_value = serialize(value);
				connection.set(byte_key, byte_value);
				return true;
			}
		});
	}
	
	public static String getWithString(final String key){
		if(redisTemplate==null) return null;
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] byte_key = redisTemplate.getStringSerializer().serialize(key);
				byte[] byte_value = connection.get(byte_key);
				if(byte_value!=null){
					return redisTemplate.getStringSerializer().deserialize(byte_value);
				}
				return null;
			}
		});
	}
	
	public static <X> X get(final String key){
		if(redisTemplate==null) return null;
		return redisTemplate.execute(new RedisCallback<X>() {
			@SuppressWarnings("unchecked")
			@Override
			public X doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] byte_key = redisTemplate.getStringSerializer().serialize(key);
				byte[] byte_value = connection.get(byte_key);
				if(byte_value!=null){
					return (X)deserialize(byte_value);
				}
				return null;
			}
		});
	}
	
	
	
	public static void del(final String key){
		if(redisTemplate==null) return;
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] byte_key = redisTemplate.getStringSerializer().serialize(key);
				connection.del(byte_key);
				return true;
			}
		});
	}
	
	public static boolean exists(final String key){
		if(redisTemplate==null) return false;
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] byte_key = redisTemplate.getStringSerializer().serialize(key);
				return connection.exists(byte_key);
			}
		});
	}
	
	
	public static void hSet(final String key , final String field , final String value){
		if(redisTemplate==null) return;
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] byte_key = redisTemplate.getStringSerializer().serialize(key);
				byte[] byte_field = redisTemplate.getStringSerializer().serialize(field);
				byte[] byte_value = redisTemplate.getStringSerializer().serialize(value);
				connection.hSet(byte_key, byte_field, byte_value);
				return true;
			}
		});
	}
	
	
	public static void hSet(final String key , final String field , final Object value){
		if(redisTemplate==null) return;
		redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] byte_key = redisTemplate.getStringSerializer().serialize(key);
				byte[] byte_field = redisTemplate.getStringSerializer().serialize(field);
				byte[] byte_value = serialize(value);
				connection.hSet(byte_key, byte_field, byte_value);
				return true;
			}
		});
	}
	
	
	public static String hGetWithString(final String key, final String field){
		if(redisTemplate==null) return null;
		return redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] byte_key = redisTemplate.getStringSerializer().serialize(key);
				byte[] byte_field = redisTemplate.getStringSerializer().serialize(field);
				byte[] byte_value = connection.hGet(byte_key, byte_field);
				if(byte_value!=null){
					return redisTemplate.getStringSerializer().deserialize(byte_value);
				}
				return null;
			}
		});
	}
	
	public static <X> X hGet(final String key, final String field){
		if(redisTemplate==null) return null;
		return redisTemplate.execute(new RedisCallback<X>() {
			@SuppressWarnings("unchecked")
			@Override
			public X doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] byte_key = redisTemplate.getStringSerializer().serialize(key);
				byte[] byte_field = redisTemplate.getStringSerializer().serialize(field);
				byte[] byte_value = connection.hGet(byte_key, byte_field);
				if(byte_value!=null){
					return (X)deserialize(byte_value);
				}
				return null;
			}
		});
	}
	
	public static Map<String , Object> hGetAll(final String key){
		if(redisTemplate==null) return null;
		return redisTemplate.execute(new RedisCallback<Map<String , Object>>() {
			@Override
			public Map<String , Object> doInRedis(RedisConnection connection) throws DataAccessException {
				Map<String , Object> valueMap = new HashMap<String, Object>(0);
				byte[] byte_key = redisTemplate.getStringSerializer().serialize(key);
				Map<byte[], byte[]> byte_valueMap = connection.hGetAll(byte_key);
				if(byte_valueMap!=null){
					Set<Entry<byte[], byte[]>> entrySet = byte_valueMap.entrySet();
					for(Entry<byte[], byte[]> entry : entrySet){
						String field = redisTemplate.getStringSerializer().deserialize(entry.getKey());
						Object value = deserialize(entry.getValue());
						valueMap.put(field, value);
					}
					return valueMap;
				}
				return null;
			}
		});
	}
	
	public static Long hDel(final String key, final String field){
		if(redisTemplate==null) return null;
		return redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] byte_key = redisTemplate.getStringSerializer().serialize(key);
				byte[] byte_field = redisTemplate.getStringSerializer().serialize(field);
				return connection.hDel(byte_key, byte_field);
			}
		});
	}
	
	public static boolean hExists(final String key, final String field){
		if(redisTemplate==null) return false;
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] byte_key = redisTemplate.getStringSerializer().serialize(key);
				byte[] byte_field = redisTemplate.getStringSerializer().serialize(field);
				return connection.hExists(byte_key, byte_field);
			}
		});
	}
	
	private static byte[] serialize(Object obj) {  
		return SerializationUtils.serialize(obj);
	}  
	
	
	private static Object deserialize(byte[] bytes) {  
		return SerializationUtils.deserialize(bytes);  
	}  
	
	@SuppressWarnings("unchecked")
	public void init(ServletContext context) {
		@SuppressWarnings("rawtypes")
		Map<String, RedisTemplate> beans = SpringContextHolder.getBeansOfType(RedisTemplate.class);
		if(beans.size()>0){
			RedisTemplate<Serializable, Serializable> redisTemplate_=null;
			try {
				redisTemplate_ = SpringContextHolder.getBean(RedisTemplate.class);
				redisTemplate_.execute(new RedisCallback<String>() {
					@Override
					public String doInRedis(RedisConnection connection) throws DataAccessException {
						return connection.ping();
					}
				});
				redisTemplate = redisTemplate_;
			} catch (Exception e) {
//				e.printStackTrace();
				System.err.println("缓存连接异常！"+e.getMessage());
			}
		}
	}

	public void destroyed(ServletContext context) {
	}
}
