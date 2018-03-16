package com.example.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.MailrecordsDaoImpl;
import com.example.exception.MailrecordsTransactionException;
import com.example.model.Mailrecords;

@Service("mailService")
public class MailServiceImpl implements IMailService{
  @Autowired
  private MailrecordsDaoImpl mailRecordsDao;
  
  public static final String FROM = "thirdwork00@gmail.com";
  
  JavaMailSender javaMailSender;
  
  @Transactional
  public void sendAndSaveMail(int num1, int num2, int total, 
    List<String> emails, Logger logger) {

	List<Mailrecords> recordList = new ArrayList<Mailrecords>();
	for(String email : emails) {    
      
      String subject = "";
      String body = "";
      
	  subject = num1 + " + " + num2 + " = " + total;
	  body = subject;
      
  	  @SuppressWarnings("resource")
	  ApplicationContext context = new ClassPathXmlApplicationContext("AppContext-*.xml");
	  MailServiceImpl ms = (MailServiceImpl) context.getBean("mailSend");
	  ms.sendMail(FROM, email, subject, body);
      
	  logger.debug(body + "\n"); 
 
	  Mailrecords records = new Mailrecords();
	  records.setNumone(num1);
	  records.setNumtwo(num2);
	  records.setSummary(total);
	  records.setEmail(email);
      Timestamp d = new Timestamp(System.currentTimeMillis());
      records.setContent(body);
      records.setTime(d);
      recordList.add(records);
    }	  
	
	try {
	  createAll(recordList);
	} catch (MailrecordsTransactionException e) {
	  e.printStackTrace();
	}
  }
  
  public void setMailSender(JavaMailSender mailSender) {
    javaMailSender = mailSender;
  }
  
  public JavaMailSender getMailSender() {
    return javaMailSender;
  }
  
  public void sendMail(String from, String to, String subject, String msg) {
	MimeMessage message = javaMailSender.createMimeMessage();

	try {
	  MimeMessageHelper helper = new MimeMessageHelper(message, true);

	  helper.setFrom(from);
	  helper.setTo(to);
	  helper.setSubject(subject);
	  helper.setText(msg);
	} catch (MessagingException e) {
	  throw new MailParseException(e);
	}

	javaMailSender.send(message);
  }

  @Override
  @Transactional
  public void createAll(List<Mailrecords> recordsList) throws MailrecordsTransactionException {
    for(Mailrecords rec : recordsList) {
    	mailRecordsDao.create(rec);	
    }
  }
}
