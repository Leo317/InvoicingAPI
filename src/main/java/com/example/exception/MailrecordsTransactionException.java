package com.example.exception;

public class MailrecordsTransactionException extends Exception{

  private static final long serialVersionUID = 3046695857251320788L;
  
  public MailrecordsTransactionException(String message) {
    super(message);
  }

}
