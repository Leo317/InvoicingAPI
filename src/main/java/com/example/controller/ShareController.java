package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.page.AjaxResponse;
import com.example.page.Response;
import com.example.page.Status;
import com.example.service.CommonTools;
import com.example.service.IShareService;

@RestController
@RequestMapping("/share")
public class ShareController {
	@Autowired
	IShareService shareServ;
	
	private static final Logger logger = LoggerFactory.getLogger(ShareController.class);
	
	@RequestMapping(value = "/getOrderList", method = RequestMethod.GET, produces = {"application/json"})
	public Response findOne(@RequestParam(value = "id", required = true, defaultValue = "0") String id) {
		// http://127.0.0.1:8080/share/getOrderList?id=1.aaa
		if (id.indexOf('.') > 0 || CommonTools.isInteger(id) == false)
			return new AjaxResponse(Status.STATUS400, "Order id : " + id + " is not an integer!!!", null);
		else {
			if (id.equals("0"))
				return new AjaxResponse(Status.SUCCESS, "", shareServ.findAll());
			else
				return new AjaxResponse(Status.SUCCESS, "", shareServ.findOne(Integer.parseInt(id)));
		}
	}
}

