package com.example.utils;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class AppPropertiesUtils {

	public static void initProperties(String activeProfile) {
    	if(activeProfile == null) {
    	   activeProfile = "dev";
    	}
    	
    	PropertySourcesPlaceholderConfigurer propertySourcePlaceholderConfigurer
    	        = new PropertySourcesPlaceholderConfigurer();
    	Resource[] resources = new ClassPathResource[]
    			{
    			  new ClassPathResource("application.properties"), 
    			  new ClassPathResource("application-" + activeProfile + ".properties")
    			};
    	propertySourcePlaceholderConfigurer.setLocations(resources);    	
    }
	
	private AppPropertiesUtils() {
		
	}
}
