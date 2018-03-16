package com.example;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.repo.SummaryRepository;


@SpringBootApplication
public class RestfulapiApplication implements CommandLineRunner{
	
	@Autowired
	SummaryRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(RestfulapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// clear all record if existed before do the tutorial with new data 
		repository.deleteAll();		
	}
	
	@Bean
	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf){    
	    return hemf.getSessionFactory();    
	}
}
