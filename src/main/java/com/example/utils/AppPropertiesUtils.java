package com.example.utils;

import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Profile("default")
public class AppPropertiesUtils {
	public static void initProperties() {
    	
    	PropertySourcesPlaceholderConfigurer propertySourcePlaceholderConfigurer
    	  = new PropertySourcesPlaceholderConfigurer();
    	Resource[] resources = new ClassPathResource[]
    	{
    	  new ClassPathResource("application.properties")
    	};
    	propertySourcePlaceholderConfigurer.setLocations(resources);    	
    }
	
	private AppPropertiesUtils() {
		
	}
}
