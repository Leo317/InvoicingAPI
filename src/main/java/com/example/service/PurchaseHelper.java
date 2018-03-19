package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.IStoreDao;
import com.example.model.Products;

@Service("purchaseHelper")
@Transactional
public class PurchaseHelper {
  @Autowired
  private IStoreDao iStoreDao;
  
  public boolean productExisted(Products prod) {
	int productId = prod.getProductId();
	if(productId == 0) {
	  return false;
	} else {
	  if(iStoreDao.exists(String.valueOf(productId))) {
	    return true;  
	  } else {
		return false;
	  }
	}
  }
  
  public void insertProduct(Products prod) {
	iStoreDao.insertProducts(prod.getProductName(), 
	  prod.getPrice(), prod.getQuantity(), prod.isAuction(), 
	  prod.getInsertTime(), prod.getUpdateTime());  
  }
  
  public void updateProduct(Products prod) {
	iStoreDao.updateProducts(prod.getProductName(), 
	  prod.getQuantity(), prod.isAuction());  
  }
}
