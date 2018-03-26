package com.example.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
  
  private static final Logger logger = LoggerFactory.getLogger(StoreController.class);
  private static final String INVALID_MESSAGE = " is invalid.";
  
  @RequestMapping(value = "/purchase", method = RequestMethod.POST, produces = {"application/json"})
  public Response purchase(@RequestBody Products[] products) {
	List<Products> list = Arrays.asList(products);
	Set<Products> set = new HashSet<>(list);
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
	  String[] auctionStrArray = 
	    { "The product id: ", productId, INVALID_MESSAGE };
	  String[] priceStrArray = 
		{ "The product price: ", price, INVALID_MESSAGE };
	  String[] quantityStrArray = 
		{ "The product quantity: ", quantity, INVALID_MESSAGE };
	  String[] productNameStrArray = 
		{ "The product name: ", productName, INVALID_MESSAGE };
      
      if(!CommonTools.isInteger(productId)) {
        return new AjaxResponse(Status.STATUS400, 
          StringUtils.join(auctionStrArray), null);	    	  
      }
      
      if(!CommonTools.isInteger(price)) {
        return new AjaxResponse(Status.STATUS400, 
          StringUtils.join(priceStrArray), null);	    	  
      }
      
      if(!CommonTools.isInteger(quantity)) {
        return new AjaxResponse(Status.STATUS400, 
          StringUtils.join(quantityStrArray), null);	    	  
      }
      
      if(!CommonTools.productNameLengthCheck(productName)) {
        return new AjaxResponse(Status.STATUS400, 
          StringUtils.join(productNameStrArray), null);	    	  
      }
      
	  String[] productExistedTrueStrArray = 
		{ "productExisted true pass, productId: ", productId };
	  String productExistedTrueMessage = 
		StringUtils.join(productExistedTrueStrArray);
	  String[] productExistedFalseStrArray = 
		{ "productExisted false pass, productId: ", productId };
	  String productExistedFalseMessage = 
		StringUtils.join(productExistedFalseStrArray);

	  String updateProductMessage = "updateProduct success.";
	  String insertProductMessage = "insertProduct success."; 

      if(purchaseHelper.productExisted(product.getProductId())) {
    	logger.debug(productExistedTrueMessage);
        purchaseHelper.updateProduct(product);
        logger.debug(updateProductMessage);
      } else {
    	logger.debug(productExistedFalseMessage);
    	purchaseHelper.insertProduct(product);
    	logger.debug(insertProductMessage);
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
