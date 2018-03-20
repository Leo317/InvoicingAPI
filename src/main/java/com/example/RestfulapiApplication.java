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
	// "客戶/共用端進銷存API"文件 by Leo
	// https://hackmd.io/xcwOtLhUToe-GDc0I8KvDw
	
	@Autowired
	IClientService clientServ;
	
	public static void main(String[] args) {
		SpringApplication.run(RestfulapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
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
