package com.example;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

@SpringBootApplication
public class RestfulapiApplication {
	// API Document
	// https://hackmd.io/xcwOtLhUToe-GDc0I8KvDwO
	
	public static void main(String[] args) {
		SpringApplication.run(RestfulapiApplication.class, args);
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
