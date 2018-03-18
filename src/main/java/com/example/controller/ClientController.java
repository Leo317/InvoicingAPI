package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Products;
import com.example.page.AjaxResponse;
import com.example.page.Response;
import com.example.page.Status;
import com.example.service.IClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	IClientService clientServ;
	
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

	@RequestMapping(value = "/getOnSaleProductsList", method = RequestMethod.GET, produces = {"application/json"})
	public List<Products> findOnSaleProducts() {
		logger.info("Controller getOnSaleProductsList");
		return clientServ.getOnSaleProductsList();
	}
	
	@RequestMapping(value = "/orderProducts", method = RequestMethod.POST, produces = {"application/json"})
	public Response orderProudcts(@RequestBody Products[] products) {
		
		for (int i = 0; i < products.length; i++) {
			if(!clientServ.getProductExist(products[i].getProductName())) {
				return new AjaxResponse(Status.STATUS400, "Product name : " + products[i].getProductName()
						+ " is not exist or not on sale!!!", null);
			} else {
				if (products[i].getQuantity() > clientServ.getProductQuantity(products[i].getProductName()))
					return new AjaxResponse(Status.STATUS400, "Product name : " + products[i].getProductName()
							+ " is not enough!!!", null);
			}
		}
		
		int orderId = clientServ.getOrderId();
		clientServ.orderProduct(orderId + 1, products);
		
		return new AjaxResponse(Status.SUCCESS, "", null);
	}
	
	@RequestMapping("/")
	public String find(){
		return "HHefwfwfewfH";
	}
	
}
