package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Products;
import com.example.page.AjaxResponse;
import com.example.page.Response;
import com.example.page.Status;
import com.example.service.CommonTools;
import com.example.service.ProductFinder;
import com.example.service.PurchaseHelper;

@RestController
public class StoreController {
  @Autowired
  PurchaseHelper purchaseHelper;
  @RequestMapping(value = "/purchase", method = RequestMethod.POST, produces = {"application/json"})
  public Response purchase(@RequestBody Products[] products) {
	List<Products> list = new ArrayList<Products>(Arrays.asList(products));
	Set<Products> set = new HashSet<Products>(list);
	if(set.size() < list.size()) {
	  return new AjaxResponse(Status.STATUS400, "The products you input "
	    + "has duplicate ones based on the same productId.", null);	
	}
    for(Products product : list) {
    	
      String productId = String.valueOf(
        product.getProductId());
      String productName = product.getProductName();
      String price = String.valueOf(
    	product.getPrice());
      String quantity = String.valueOf(
        product.getQuantity());
      
      if(!CommonTools.isInteger(productId)) {
        return new AjaxResponse(Status.STATUS400, "The product id: " 
          + productId
          + " is invalid.", null);	    	  
      }
      
      if(!CommonTools.isInteger(price)) {
        return new AjaxResponse(Status.STATUS400, "The product price: " 
          + price
          + " is invalid.", null);	    	  
      }
      
      if(!CommonTools.isInteger(quantity)) {
        return new AjaxResponse(Status.STATUS400, "The product quantity: " 
          + quantity
          + " is invalid.", null);	    	  
      }
      
      if(!CommonTools.productNameLengthCheck(productName)) {
        return new AjaxResponse(Status.STATUS400, "The product name: " 
          + productName
    	  + " is out of length.", null);	    	  
      }
      
      if(purchaseHelper.productExisted(product.getProductId())) {
        purchaseHelper.updateProduct(product);
      } else {
    	purchaseHelper.insertProduct(product);  
      }
    }
    
	return new AjaxResponse(Status.SUCCESS, "", null);
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
        List<Products> result = new ArrayList<Products>();
        result = productFinder.findByCond(keyword, true);
        result.addAll(productFinder.findByCond(keyword, false));
        return new AjaxResponse(Status.SUCCESS, "", result);
      } else {
    	if((auctionStr.compareTo("true") != 0) &&
    	   (auctionStr.compareTo("false") != 0)) {
          return new AjaxResponse(Status.STATUS400, "The auction parameter: " 
                                  + auctionStr
                                  + " is invalid. It should be \"true\" or \"false\"", 
                                  null);   		
    	}
        boolean auction = Boolean.parseBoolean(auctionStr);
        return new AjaxResponse(Status.SUCCESS, "", productFinder.findByCond(keyword, auction));
      }
    }
  }
}
