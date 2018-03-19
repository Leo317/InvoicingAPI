package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.IShareService;
import com.example.view.OrdersDTO;

@RestController
@RequestMapping("/share")
public class ShareController {
	@Autowired
	IShareService shareServ;
	
	private static final Logger logger = LoggerFactory.getLogger(ShareController.class);

	@RequestMapping(value = "/getOrdersList", method = RequestMethod.GET, produces = {"application/json"})
	public List<OrdersDTO> findAll() {
		logger.info("ShareController getOrdersList");
		return shareServ.findAll();
	}
	
	@RequestMapping(value = "/getOrderList/{id}", method = RequestMethod.GET, produces = {"application/json"})
	public OrdersDTO findOne(@PathVariable int id) {
		logger.info("ShareController getOrderListById");
		return shareServ.findOne(id);
	}
	
}
