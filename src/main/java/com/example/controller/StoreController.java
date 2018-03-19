package com.example.controller;

import java.util.List;

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
import com.example.service.ProductFinder;
import com.example.service.PurchaseHelper;

@RestController
public class StoreController {
  @Autowired
  PurchaseHelper purchaseHelper;
  @RequestMapping(value = "/purchase", method = RequestMethod.POST, produces = {"application/json"})
  public Response purchase(@RequestBody Products[] products) {
    for(Products product : products) {
      if(purchaseHelper.productExisted(product)) {
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
  public List<Products> list(@RequestParam(value="keyword", defaultValue="") String keyword,
		  					 @RequestParam(value="auction", defaultValue="false") boolean auction) {
    if(StringUtils.isEmpty(keyword)) {
      productFinder.findAll();
    }
	return productFinder.findByCond(keyword, auction);
  }
}
