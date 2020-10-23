package com.test.cacheservice.service;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.test.cacheservice.model.Cache;;



@Service
public class CacheService {
	
	  private Logger logger = LoggerFactory.getLogger(this.getClass());
	  private ConcurrentHashMap<String, Cache> store = new ConcurrentHashMap<>();
	  
	
	
	   public void creatCache(String name, Integer capacity) {
		   
		   
		   
		      if (capacity == null) {
		    	  logger.info("create a cache with default capacity ");
		    	  this.store.put(name, new Cache());
		      } else {
		    	  logger.info("create a cache with capacity is " + capacity);
		          this.store.put(name, new Cache(capacity));
		      }
		   
	   }
	
	 
       public void add(String hashName, String key, String obj) {
    	   
    	   logger.info("add item key is:" + key + " and value is: "  + obj + " to  cache " + hashName);
    	   
    	   if (this.store.get(hashName) == null) {
    		   this.creatCache(hashName, null);   
    	   } 
    		   
    	   this.store.get(hashName).add(key, obj);
    	   
    	       
       }
       
       public String get(String hashName, String key) {
    	   
    	   logger.info("get item key is:" + key + " from  cache " + hashName);
    	   
    	   if (this.store.get(hashName) != null) {
    		   return this.store.get(hashName).get(key);
    	   }
    	   
    	   return null;
       }
       
       public void delete(String hashName, String key) {
    	   
    	   logger.info("delete item key is:" + key + " from  cache " + hashName);
    	   
    	   if (this.store.get(hashName) != null) {
    	       this.store.get(hashName).delete(key);
    	       if (this.store.get(hashName).getCurrentSize() == 0) {
    	    	   this.store.remove(hashName);
    	       }
    	   }
       }

}
