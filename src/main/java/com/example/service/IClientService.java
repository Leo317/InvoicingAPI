package com.example.service;

import java.util.List;

import com.example.model.Products;

public interface IClientService {
	
	public List<Products> getOrderableProductsList();
	public boolean getProductExist(String productName);
	public int getProductQuantity(String productName);
	public int getOrderId();
	public int getProductPrice(String productName);
	
	public void orderProduct(int orderId, List<Products> products);
	
}
