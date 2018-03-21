package com.example.dao;

import java.util.List;

import com.example.model.Orders;
import com.example.model.Products;

public interface IClientDao {
	
	public void initProduct(Products product);
	public void initOrder(Orders order);
	
	public List<Products> getOrderableProductsList();
	public boolean getProductExist(String productName);
	public int getProductQuantity(String productName);
	public int getOrderId();
	public int getProductPrice(String productName);
	
	public void orderProducts(Orders order);
	public Products getProductInfo(String productName);
	public void updateProductQuantity(Products product);
	
}
