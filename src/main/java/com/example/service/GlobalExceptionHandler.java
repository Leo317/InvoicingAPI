package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.page.AjaxResponse;
import com.example.page.Response;
import com.example.page.Status;

@ControllerAdvice
public class GlobalExceptionHandler {
  private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ResponseBody
  @ExceptionHandler(Exception.class)
  public Response handleException(Exception e) {
	String msg = e.getMessage();
	if(StringUtils.isEmpty(msg)) {
	  msg = "Server exception occurred.";
	}
	logger.error(e.getMessage());
	return new AjaxResponse(Status.ERROR, msg, null);	  
  }
}
