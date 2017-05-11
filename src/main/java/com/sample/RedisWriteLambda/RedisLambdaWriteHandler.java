package com.sample.RedisWriteLambda;

import java.util.Map;

import org.springframework.web.client.RestTemplate;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sample.response.EmployeeResponse;

/**
 * Try and connect to Redis
 *
 */

public class RedisLambdaWriteHandler implements RequestHandler<Map<String,String>, Object> {

	public Object handleRequest(Map<String, String> input, Context context) {

	    //address of redis server
		final String redisHost = "localhost"; 
				
		final Integer redisPort = 6379;
	 
	    //the jedis connection pool..
	   //configure our pool connection
	    JedisPool pool = new JedisPool(redisHost, redisPort);
	    
	    //Add the input to Redis
        addHash(input,pool);
        //EmployeeResponse emp= new EmployeeResponse("Juliet","TCS","London");
        
        //Read the value from Redis and return
		return readRedis();
        //return emp;
	}
	
 
    private String readRedis() {
    	RestTemplate restTemplate = new RestTemplate();
    	//API endpoint url for Lambda function
    	String readUrl ="API_GW_URL";
    	
        //Call to the Lambda function performing read on Redis    	
    	String response = restTemplate.getForObject(readUrl, String.class, "Employee");
    	System.out.println("Read Output:" + response);
		return response;		
	}


	public void addHash(Map<String, String> input, JedisPool pool) {
        //adding some values in Redis HASH
    	String key = "Employee";
 
        Jedis jedis = pool.getResource();
        
        try {
        	
            //save to redis
            jedis.hmset(key, input);
 
            //setting TTL for the key
            jedis.expire(key, 300);
 
        } catch (JedisException e) {
            //return Jedis instance to the pool
            if (null != jedis) {
                pool.returnBrokenResource(jedis);
                jedis = null;
            }
        } finally {
            ///return Jedis instance to the pool
            if (null != jedis)
                pool.returnResource(jedis);
        }
    }
}

