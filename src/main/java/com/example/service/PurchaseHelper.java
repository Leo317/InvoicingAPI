package com.example.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
  
  public boolean productExisted(int productId) {
	List<Products> prodList = new ArrayList<Products>();
	prodList = iStoreDao.productExisted(productId);
    if((prodList != null) && (!prodList.isEmpty())) {
      return true;	
    } else {
      return false;	
    }
  }
  
  public void insertProduct(Products prod) {
	prod.setCreateTime(
	  new Timestamp(System.currentTimeMillis()));
	prod.setUpdateTime(
	  new Timestamp(System.currentTimeMillis()));
	iStoreDao.save(prod);
  }
  
  public void updateProduct(Products prod) {
	iStoreDao.updateProducts(
	  prod.getProductId(), 
	  prod.getProductName(),
	  prod.getPrice(), 
	  prod.getQuantity(), 
	  prod.isAuction(),
	  new Timestamp(System.currentTimeMillis()));  
  }
}
