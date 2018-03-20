package com.example.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
	List<Products> list = new ArrayList<Products>(Arrays.asList(products));
	Collection<Products>  prodCollection =  list.stream().collect(
	  Collectors.collectingAndThen(Collectors.toMap(Products::getProductId, 
	    Function.identity(), (left, right) -> {
          left.setQuantity(left.getQuantity() + right.getQuantity());
            return left;
    }), m -> new ArrayList<>(m.values())));
    for(Products product : prodCollection) {
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
  public List<Products> list(@RequestParam(value="keyword", defaultValue="") String keyword,
		  					 @RequestParam(value="auction", defaultValue="") String auctionStr) {
    if(StringUtils.isEmpty(keyword) && 
       StringUtils.isEmpty(auctionStr)) {
      return productFinder.findAll();
    } else {
      boolean auction = Boolean.parseBoolean(auctionStr);
      return productFinder.findByCond(keyword, auction);    	
    }
  }
}
