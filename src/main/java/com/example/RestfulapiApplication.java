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
import com.example.utils.DevAppPropertiesUtils;

@SpringBootApplication
public class RestfulapiApplication {
	// API Document
	// https://hackmd.io/xcwOtLhUToe-GDc0I8KvDwO
	
//	@Autowired
//	SummaryRepository repository;
	
	private static final Logger logger = 
        LoggerFactory.getLogger(RestfulapiApplication.class);
	private static final String DEFAULT_PROFILE = "--spring.profiles.active=default";
		
	public static void main(String[] args) {
		SpringApplication.run(RestfulapiApplication.class, args);
//		if(args == null || args[0].equals(DEFAULT_PROFILE)) {
//		  AppPropertiesUtils.initProperties();
//		  printEnvironmentsProperties("default");
//		} else {
//		  DevAppPropertiesUtils.initProperties();
//		  printEnvironmentsProperties("dev");
//		}
//        ConfigurableApplicationContext context = SpringApplication.run(RestfulapiApplication.class, args);
//        context.getBean(RestfulapiApplication.class);
	}
	
//	@Override
//	public void run(String... args) throws Exption {
//		respository.deleteAll();
//	}
	
//	private static void printEnvironmentsProperties(String envName) {
//	    StringBuilder stringBuilder = new StringBuilder("Properties Files ......\n");
//	    stringBuilder.append("The name of properties file").append(" : ").append(envName).append("\n\n\n");
//	    logger.info(stringBuilder.toString());
//	}
	
	@Bean
	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf){    
	    return hemf.getSessionFactory();    
	}
	
//	@Autowired
//    @Bean
//    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//		return new HibernateTransactionManager(sessionFactory);
//    }
}
