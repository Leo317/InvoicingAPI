package com.example.controller;

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
import com.example.service.CommonTools;
import com.example.service.IClientService;
import com.example.view.ProductsDTO;

@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	IClientService clientServ;
	
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

	@RequestMapping(value = "/getOnSaleProductsList", method = RequestMethod.GET, produces = {"application/json"})
	public Response findOnSaleProducts() {
		return new AjaxResponse(Status.SUCCESS, "", clientServ.getOnSaleProductsList());
	}
	
	@RequestMapping(value = "/orderProducts", method = RequestMethod.POST, produces = {"application/json"})
	public Response orderProudcts(@RequestBody ProductsDTO[] productsDTO) {
		for (int i = 0; i < productsDTO.length; i++) {
			if(!clientServ.getProductExist(productsDTO[i].getProductName())) {
				return new AjaxResponse(Status.STATUS400, "Product name : " + productsDTO[i].getProductName()
						+ " is not exist or not on sale!!!", null);
			} else {
				if (productsDTO[i].getQuantity().indexOf('.') > 0 || CommonTools.isInteger(productsDTO[i].getQuantity()) == false)
					return new AjaxResponse(Status.STATUS400, "Proudct name : " + productsDTO[i].getProductName() + ", its quantity : " + productsDTO[i].getQuantity() + " is not an integer!!!", null);
				else {
					if (Integer.parseInt(productsDTO[i].getQuantity()) - clientServ.getProductQuantity(productsDTO[i].getProductName()) > 0)
						return new AjaxResponse(Status.STATUS400, "Product name : " + productsDTO[i].getProductName()
								+ " is not enough!!!", null);
				}
			}
		}
		
		Products[] products = new Products[productsDTO.length];
		for (int i = 0; i < productsDTO.length; i++) {
			Products temp = new Products();
			temp.setProductName(productsDTO[i].getProductName());
			temp.setQuantity(Integer.parseInt(productsDTO[i].getQuantity()));
			
			products[i] = temp;
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
