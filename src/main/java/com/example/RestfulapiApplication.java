package com.example;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

import com.example.utils.AppPropertiesUtils;

@SpringBootApplication
public class RestfulapiApplication {
	// API Document
	// https://hackmd.io/xcwOtLhUToe-GDc0I8KvDwO
	
	private static final Logger logger = 
        LoggerFactory.getLogger(RestfulapiApplication.class); 
		
	public static void main(String[] args) {
		AppPropertiesUtils.initProperties(args[0]);
		printEnvironmentsProperties(args[0]);
        ConfigurableApplicationContext context = SpringApplication.run(RestfulapiApplication.class, args);
        context.getBean(RestfulapiApplication.class);
	}
	
	private static void printEnvironmentsProperties(String envName) {
	    StringBuilder stringBuilder = new StringBuilder("Properties Files ......\n");
	    stringBuilder.append("The name of properties file").append(" : ").append(envName).append("\n\n\n");
	    logger.info(stringBuilder.toString());
	}
	
	@Bean
	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf){    
	    return hemf.getSessionFactory();    
	}
	
	@Autowired
    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
    }
}
