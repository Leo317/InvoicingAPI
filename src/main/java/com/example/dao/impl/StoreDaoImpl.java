package com.example.dao.impl;

import java.sql.Timestamp;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.Products;

@Repository("storeDao")
public class StoreDaoImpl {
	
  @Autowired
  SessionFactory sessionFactory;
  
  public Products getProductByProductId(int productId) {
	    return (Products) this.sessionFactory
	    		.getCurrentSession()
	    		.createQuery(" from Products where product_id=:productId ")
	    		.setParameter("productId", productId)
	    		.uniqueResult();
  }
	  
  public void updateProduct(Products prod) {
		Products tempProd = getProductByProductId(prod.getProductId());
		tempProd.setProductName(prod.getProductName());
		tempProd.setPrice(prod.getPrice());
		tempProd.setQuantity(prod.getQuantity());
		tempProd.setAuction(prod.isAuction());
		tempProd.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		this.sessionFactory.getCurrentSession().update(tempProd);
  }

}
