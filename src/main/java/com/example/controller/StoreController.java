package com.example.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Products;
import com.example.page.AjaxResponse;
import com.example.page.Response;
import com.example.page.Status;
import com.example.service.ProductFinder;
import com.example.service.PurchaseHelper;

@RestController
public class StoreController {
  @Autowired
  PurchaseHelper purchaseHelper;
  
  @RequestMapping(value = "/purchase", method = RequestMethod.POST, produces = {"application/json"})
  public Response purchase(@RequestBody Products[] products) {
	List<Products> list = Arrays.asList(products);
	Set<Products> set = new HashSet<>(list);
	if(set.size() < list.size()) {
	  return new AjaxResponse(Status.STATUS400, "The products you input "
	    + "has duplicate ones based on the same productId.", null);	
	}
    for(Products product : list) {

      purchaseHelper.insertProduct(product);

    }    
    
	return new AjaxResponse(Status.SUCCESS, "", null);
  }
  
  @RequestMapping(value = "/purchase/{id}", method = RequestMethod.PUT)
  public @ResponseBody Products updatePurchase(@PathVariable("id") int productId, 
			                                   @RequestParam String productName, 
			                                   @RequestParam int price,
			                                   @RequestParam int quantity,
			                                   @RequestParam boolean auction,
			                                   @RequestParam String updateTime) { 

    Products products = new Products();
    products.setProductId(productId);
    products.setProductName(productName);
    products.setPrice(price);
    products.setQuantity(quantity);
    products.setAuction(auction);
    
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat datetimeFormatter1 = 
      new SimpleDateFormat(pattern);    
    Date date = null;
	try {
		date = datetimeFormatter1.parse(updateTime);
	} catch (ParseException e) {
		
	}
	date = date == null ? new Date() : date;
	long timeValue = date.getTime();
    Timestamp updateTimestamp = new Timestamp(timeValue);
    products.setUpdateTime(updateTimestamp);
    purchaseHelper.updateProduct(products);
    return products;	
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
}
