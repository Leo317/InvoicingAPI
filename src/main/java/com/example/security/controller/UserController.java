package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.page.AjaxResponse;
import com.example.page.Response;
import com.example.page.Status;
import com.example.security.model.AccountCredentials;
import com.example.security.service.AccountService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	AccountService accoutService;
	
	@RequestMapping(value = "/create", 
	  method = RequestMethod.POST, 
	  produces = { "application/json" })
	public Response addUser(@RequestBody AccountCredentials user) {

		accoutService.insertAccount(user);

		return new AjaxResponse(Status.SUCCESS, "", null);
	}
}
