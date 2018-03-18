package com.example;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

import com.example.service.IClientService;

@SpringBootApplication
public class RestfulapiApplication implements CommandLineRunner{

	@Autowired
	IClientService clientServ;
	
	public static void main(String[] args) {
		SpringApplication.run(RestfulapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// clear all record if existed before do the tutorial with new data
		clientServ.init();
	}
	
	@Bean
	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf){    
	    return hemf.getSessionFactory();    
	}
	
	@Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
 
        return transactionManager;
    }
}
