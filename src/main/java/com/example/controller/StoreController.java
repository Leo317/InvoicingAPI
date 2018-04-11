package com.example.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Products;
import com.example.page.AjaxResponse;
import com.example.page.Response;
import com.example.page.Status;
import com.example.security.model.RoleNames;
import com.example.security.service.TokenAuthenticationService;
import com.example.service.ProductFinder;
import com.example.service.PurchaseHelper;

@RestController
@RequestMapping("/store")
public class StoreController {
  public static final String ACCESS_DENIED = "Access Denied.";
  
  @Autowired
  PurchaseHelper purchaseHelper;
  
  @RequestMapping(value = "/purchase", method = RequestMethod.POST, produces = {"application/json"})
  public Response purchase(@RequestBody Products[] products, HttpServletRequest request) {
	String[] token = request.getHeader(TokenAuthenticationService.HEADER_STRING).split(" ");
		if (token[1].compareTo(RoleNames.STORE.toString()) == 0) {

			List<Products> list = Arrays.asList(products);
			for (Products product : list) {

				purchaseHelper.insertProduct(product);

			}

			return new AjaxResponse(Status.SUCCESS, "", null);
		} else {
			return new AjaxResponse(Status.ERROR, ACCESS_DENIED, null);
	    }
  }
  
  @RequestMapping(value = "/purchase/{id}", method = RequestMethod.POST, produces = {"application/json"})
  public Response updatePurchase(@PathVariable("id") int productId, 
		  					   @RequestBody Products products,
		  					   HttpServletRequest request) {
	String[] token = request.getHeader(TokenAuthenticationService.HEADER_STRING).split(" ");
	if (token[1].compareTo(RoleNames.STORE.toString()) == 0) {
	  purchaseHelper.updateProduct(products);
		return new AjaxResponse(Status.SUCCESS, "", null);
	} else {
		return new AjaxResponse(Status.ERROR, ACCESS_DENIED, null);	  
	}
  }
  
  @Autowired
  ProductFinder productFinder;
  @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json"})
  public Response list(@RequestParam(value="keyword", defaultValue="") String keyword,
		  					 @RequestParam(value="auction", defaultValue="") String auctionStr,
		  					 HttpServletRequest request) {
    String[] token = request.getHeader(TokenAuthenticationService.HEADER_STRING).split(" ");
		if (token[1].compareTo(RoleNames.STORE.toString()) == 0) {
			if (StringUtils.isEmpty(keyword) && StringUtils.isEmpty(auctionStr)) {
				return new AjaxResponse(Status.SUCCESS, "", productFinder.findAll());
			} else {
				if (StringUtils.isEmpty(auctionStr)) {
					return new AjaxResponse(Status.SUCCESS, "", productFinder.findByKeyword(keyword));
				} else {
					if ((auctionStr.compareTo("true") != 0) && (auctionStr.compareTo("false") != 0)) {
						String[] strArray = { "The auction parameter: ", auctionStr,
								" is invalid. It should be \"true\" or \"false\"" };
						return new AjaxResponse(Status.STATUS400, StringUtils.join(strArray), null);
					}
					boolean auction = Boolean.parseBoolean(auctionStr);
					return new AjaxResponse(Status.SUCCESS, "", productFinder.findByCond(keyword, auction));
				}
			}
		} else {
			return new AjaxResponse(Status.ERROR, ACCESS_DENIED, null);			
		}
  }
  
  @RequestMapping(value = "/getToken", method = RequestMethod.GET)
  public String[] getToken(HttpServletRequest request) {
	  return request.getHeader(TokenAuthenticationService.HEADER_STRING).split(" ");
  }
}
