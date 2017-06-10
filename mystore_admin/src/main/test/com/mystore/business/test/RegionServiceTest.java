package com.mystore.business.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mystore.business.annotation.ExcludeAnnotationExclusionStrategy;
import com.mystore.business.dto.Region;
import com.mystore.business.service.RegionService;

public class RegionServiceTest {
	
	public static void main(String[] args) throws Exception{  
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:*.xml");  
        
        RegionService regionService = (RegionService)ac.getBean("regionService");
        List<Region> list = regionService.selectAllRegion();
        
        if(list != null && list.size() > 0){
        	Gson gson = new GsonBuilder() 
        	.setExclusionStrategies(new ExcludeAnnotationExclusionStrategy()) 
        	.create();
        	
        	 System.out.println(gson.toJson(list));
        }
        
    }  

}
