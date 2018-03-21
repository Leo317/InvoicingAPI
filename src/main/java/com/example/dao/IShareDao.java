package com.example.dao;

import java.util.List;

import com.example.model.Orders;

public interface IShareDao {
	
	public List<Orders> findAll();
	public int getProductPrice(String productName);
	public void updateProductPrice(String productName, int price);
	public List<Orders> findOne(int id);
}
