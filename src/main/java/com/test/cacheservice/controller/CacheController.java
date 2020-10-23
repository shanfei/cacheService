package com.test.cacheservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.cacheservice.service.CacheService;

@RestController
public class CacheController {
	
	   @Autowired
	   private CacheService cacheService;
	   
	   @PostMapping("/api/v1/cache/{name}")
	   public void createCache(@PathVariable String name, 
			                   @RequestParam Integer capacity) {
		      if (capacity == null) {
		    	  cacheService.creatCache(name,100);
		      } else {
		    	  cacheService.creatCache(name,capacity);
		      }
	   }
	   
	
	   @PostMapping("/api/v1/cache/{name}/key/{key}")
	   public void addCacheItem(@PathVariable String key,
			                    @PathVariable String name,
			                    @RequestBody String item) {
		   
		       cacheService.add(name, key, item);
		      
	   }
	   
	   @GetMapping("/api/v1/cache/{name}/key/{key}")
	   public ResponseEntity<String> getCacheItem(
	              @PathVariable String name,
			      @PathVariable String key) {
		      String item = cacheService.get(name,key);
		      if (null == item) {
		    	  return ResponseEntity.notFound().build();
		      }
		      
		      return ResponseEntity.ok(item);
	   }
	   
	   @DeleteMapping("/api/v1/cache/{name}/key/{key}")
	   public void deleteCacheItem(
	           @PathVariable String name,
			   @PathVariable String key) {
		      cacheService.delete(name,key);
	   }
	   

}
