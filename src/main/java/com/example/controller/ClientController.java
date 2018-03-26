package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Products;
import com.example.page.AjaxResponse;
import com.example.page.Response;
import com.example.page.Status;
import com.example.service.ClientService;
import com.example.view.ProductsDTO;

@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	ClientService clientServ;

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

	@RequestMapping(value = "/getOrderableProductsList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response findOrderableProducts() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Controller");
		System.out.println(clientServ.getOrderableProductsList());
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return new AjaxResponse(Status.SUCCESS, "", clientServ.getOrderableProductsList());
	}

	@RequestMapping(value = "/orderProducts", method = RequestMethod.POST, produces = { "application/json" })
	public Response orderProudcts(@RequestBody List<ProductsDTO> productsDTOs) {
		if (productsDTOs != null && !productsDTOs.isEmpty()) {
			for (ProductsDTO productsDTOInput : productsDTOs) {
				StringBuilder str = new StringBuilder();
				str.append("The product name : ");
				str.append(productsDTOInput.getProductName());
				if (StringUtils.isBlank(productsDTOInput.getProductName())) {
					str.append(" is error input.");
					return new AjaxResponse(Status.STATUS400, str.toString(), null);
				}
				if (!NumberUtils.isDigits(productsDTOInput.getQuantity())) {
					str.append(", its quantity : ");
					str.append(productsDTOInput.getQuantity());
					str.append(" is not an integer!!!");
					return new AjaxResponse(Status.STATUS400, str.toString(), null);
				}
				if (Integer.parseInt(productsDTOInput.getQuantity())
						- clientServ.getProductQuantity(productsDTOInput.getProductName()) > 0) {
					str.append(" is not enough!!!");
					return new AjaxResponse(Status.STATUS400, str.toString(), null);
				}
				
				List<Products> products = new ArrayList<>();
				
				for (ProductsDTO productsDTO : productsDTOs) {
					Products temp = new Products();
					temp.setProductName(productsDTO.getProductName());
					temp.setQuantity(Integer.parseInt(productsDTO.getQuantity()));
					products.add(temp);
				}
				
				int orderId = clientServ.getOrderId();
				clientServ.orderProudcts(orderId + 1, products);
				
				return new AjaxResponse(Status.SUCCESS, "", null);
			}
		}
		return new AjaxResponse(Status.ERROR, "paramter is null", null);
	}
	
}
