package com.example.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwthandler.TokenAuthenticationService;
import com.example.model.Products;
import com.example.page.AjaxResponse;
import com.example.page.Response;
import com.example.page.Status;
import com.example.service.ProductFinder;
import com.example.service.PurchaseHelper;

@RestController
@RequestMapping("/store")
@PreAuthorize("hasRole('ROLE_STORE')")
public class StoreController {
  @Autowired
  PurchaseHelper purchaseHelper;
  
  @RequestMapping(value = "/purchase", method = RequestMethod.POST, produces = {"application/json"})
  public Response purchase(@RequestBody Products[] products) {
	List<Products> list = Arrays.asList(products);
    for(Products product : list) {

      purchaseHelper.insertProduct(product);

    }    
    
	return new AjaxResponse(Status.SUCCESS, "", null);
  }
  
  @RequestMapping(value = "/purchase/{id}", method = RequestMethod.POST, produces = {"application/json"})
  public String updatePurchase(@PathVariable("id") int productId, 
		  					   @RequestBody Products products) {

    purchaseHelper.updateProduct(products);
    return Status.SUCCESS.toString();	
  }
  
  @Autowired
  ProductFinder productFinder;
  @RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json"})
  public Response list(@RequestParam(value="keyword", defaultValue="") String keyword,
		  					 @RequestParam(value="auction", defaultValue="") String auctionStr) {
    if(StringUtils.isEmpty(keyword) && 
       StringUtils.isEmpty(auctionStr)) {
      return new AjaxResponse(Status.SUCCESS, "", productFinder.findAll());
    } else {
      if(StringUtils.isEmpty(auctionStr)) {    
        return new AjaxResponse(Status.SUCCESS, "", 
          productFinder.findByKeyword(keyword));
      } else {
    	if((auctionStr.compareTo("true") != 0) &&
    	   (auctionStr.compareTo("false") != 0)) {
    		String[] strArray = 
    		  { "The auction parameter: ", auctionStr, " is invalid. It should be \"true\" or \"false\"" };
          return new AjaxResponse(Status.STATUS400, 
        		                  StringUtils.join(strArray), 
                                  null);   		
    	}
        boolean auction = Boolean.parseBoolean(auctionStr);
        return new AjaxResponse(Status.SUCCESS, "", productFinder.findByCond(keyword, auction));
      }
    }
  }
  
  @RequestMapping(value = "/getToken", method = RequestMethod.GET)
  public String getToken(HttpServletRequest request) {
	  String[] parts = request.getHeader(TokenAuthenticationService.HEADER_STRING).split(" ");
	  return parts[1];
  }
}
