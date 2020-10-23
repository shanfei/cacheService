package com.test.cacheservice.model;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cache {
	
	   private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	   private int capacity = 100;
	   
	   private ConcurrentHashMap<String,String> storeMap  = new ConcurrentHashMap();
	   
	   private Deque<String> doubleQueue = new LinkedList<>();
	   
	   public Cache() {
		   
	   }
	   
	   public Cache(int capacity) {
		   
		   this.capacity = capacity;
				   
	   }
	   
	   public int getCurrentSize() {
		   return this.doubleQueue.size();
	   }
	   
	   public void add(String key, String value) {
		   
		   String obj = storeMap.get(key);
		   if (obj == null) {
			   if (doubleQueue.size() == capacity) {
				   
				    logger.debug(" doubleQueue size is: " + doubleQueue.size());
				   
				    String removedKey = doubleQueue.removeLast();
				    storeMap.remove(removedKey);
				    
				    logger.debug(" doubleQueue size is: " + doubleQueue.size());
				    
				    doubleQueue.addFirst(key);
			   }
			   storeMap.put(key, value);  
		   } else {
			   doubleQueue.remove(key);
			   doubleQueue.addFirst(key);
		   }
		   
		   storeMap.put(key, value);
	   }
	   
	   
	   public String get(String key) {
		   String objStr = storeMap.get(key);
		   
		   doubleQueue.remove(key);
		   doubleQueue.addFirst(key);
		   
		   return objStr;
	   }
	   
	   public void delete(String key) {
		    storeMap.remove(key);
		    doubleQueue.remove(key);
	   }
	   

}
