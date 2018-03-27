package com.example.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.IStoreDao;
import com.example.dao.impl.StoreDaoImpl;
import com.example.model.Products;

@Service("purchaseHelper")
@Transactional
public class PurchaseHelper {
  @Autowired
  private IStoreDao iStoreDao;
  
  public void insertProduct(Products prod) {
	prod.setCreateTime(
	  new Timestamp(System.currentTimeMillis()));
	prod.setUpdateTime(
	  new Timestamp(System.currentTimeMillis()));
	iStoreDao.save(prod);
  }
  
  @Autowired
  private StoreDaoImpl storeDaoImpl;
  public void updateProduct(Products prod) {
	  storeDaoImpl.updateProduct(prod);
  }
}
