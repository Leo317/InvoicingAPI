package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.page.AjaxResponse;
import com.example.page.Response;
import com.example.page.Status;
import com.example.service.CommonTools;
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
		return shareServ.findAll();
	}
	
	@RequestMapping(value = "/getOrderList/{id}", method = RequestMethod.GET, produces = {"application/json"})
	public Response findOne(@PathVariable String id) {
		if (id.indexOf('.') != -1 || CommonTools.isInteger(id) == false)
			return new AjaxResponse(Status.STATUS400, "Order id : " + id + " is not an integer!!!", null);
		else {
			return new AjaxResponse(Status.SUCCESS, "", shareServ.findOne(Integer.parseInt(id)));
		}
	}
}

