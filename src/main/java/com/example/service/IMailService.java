package com.example.service;

import java.util.List;

import com.example.exception.MailrecordsTransactionException;
import com.example.model.Mailrecords;

public interface IMailService {
	
  public void createAll(List<Mailrecords> recordsList) throws MailrecordsTransactionException;

}
