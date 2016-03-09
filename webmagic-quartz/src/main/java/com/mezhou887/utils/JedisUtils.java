package com.mezhou887.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

public class JedisUtils<K, V> {

	@Autowired  
    protected RedisTemplate<K, V> redisTemplate; 
	
	public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    }  
      
    protected RedisSerializer<String> getRedisSerializer() {  
        return redisTemplate.getStringSerializer();  
    }  
    
    public void write(final String key, final String message)  {
    	redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(redisTemplate.getStringSerializer().serialize(key), redisTemplate.getStringSerializer().serialize(message));
				return null;
			}
    	});
    }
    
    public String read(final String key) {
    	return redisTemplate.execute(new RedisCallback<String>() {
    		public String doInRedis(RedisConnection connection) throws DataAccessException {
    			byte[] keyByte = key.getBytes();
    			if(connection.exists(keyByte)) {
    				byte[] value = connection.get(keyByte);  
                    String message = redisTemplate.getStringSerializer().deserialize(value);
                    return message;
    			}
    			return null;
    		}
    	});
    }

}
