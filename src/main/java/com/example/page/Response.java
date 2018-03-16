package com.example.page;

public interface Response {

  public void setStatus(Status status);
  public Status getStatus();
  public void setMessage(String message);
  public String getMessage();
  public void setResult(Object result);
  public Object getResult();
}
