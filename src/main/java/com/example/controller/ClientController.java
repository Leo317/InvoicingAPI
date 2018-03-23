package com.example.controller;

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
import com.example.service.CommonTools;
import com.example.service.IClientService;
import com.example.view.ProductsDTO;

@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	IClientService clientServ;

	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

	@RequestMapping(value = "/getOrderableProductsList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response findOrderableProducts() {
		return new AjaxResponse(Status.SUCCESS, "", clientServ.getOrderableProductsList());
	}

	@RequestMapping(value = "/orderProducts", method = RequestMethod.POST, produces = { "application/json" })
	public Response orderProudcts(@RequestBody ProductsDTO[] productsDTOs) {
		if (productsDTOs != null && productsDTOs.length > 0) {
			for (ProductsDTO productsDTO : productsDTOs) {
				StringBuilder str = new StringBuilder();
				str.append("The product name : ");
				str.append(productsDTO.getProductName());
				if (StringUtils.isBlank(productsDTO.getProductName())) {
					str.append(" is error input.");

					return new AjaxResponse(Status.STATUS400, str.toString(), null);
				}

				if (!NumberUtils.isDigits(productsDTO.getQuantity())) {
					str.append(", its quantity : ");
					str.append(productsDTO.getQuantity());
					str.append(" is not an integer!!!");

					return new AjaxResponse(Status.STATUS400, str.toString(), null);
				}

				if (Integer.parseInt(productsDTO.getQuantity())
						- clientServ.getProductQuantity(productsDTO.getProductName()) > 0) {
					str.append(" is not enough!!!");

					return new AjaxResponse(Status.STATUS400, str.toString(), null);
				}
			}
			
			Products[] products = new Products[productsDTOs.length];
			int index = 0;
			
			for (ProductsDTO productsDTO : productsDTOs) {
				Products temp = new Products();
				temp.setProductName(productsDTO.getProductName());
				temp.setQuantity(Integer.parseInt(productsDTO.getQuantity()));
				products[index] = temp;
				index++;
			}
			
			int orderId = clientServ.getOrderId();
			clientServ.orderProduct(orderId + 1, products);

			return new AjaxResponse(Status.SUCCESS, "", null);
		} else {
			return new AjaxResponse(Status.ERROR, "paramter is null", null);
		}
		
	}

	@RequestMapping("/")
	public String find() {
		return "HHefwfwfewfH";
	}

}
