package com.example.page;


public class AjaxResponse implements Response {

  private Status status;
  private String message;
  private Object result;
  
  public AjaxResponse() {
    
  }
  
  public AjaxResponse(Status status, String message, Object result) {
    this.status = status;
    this.message = message;
    this.result = result;
  }
  
  @Override
  public void setStatus(Status status) {
    this.status = status;
  }
  
  @Override
  public Status getStatus() {
    return status;
  }
  
  @Override
  public void setMessage(String message) {
    this.message = message;
  }
  
  @Override
  public String getMessage() {
    return message;
  }
  
  @Override
  public void setResult(Object result) {
    this.result = result;
  }
  
  @Override
  public Object getResult() {
    return result;
  }
}
