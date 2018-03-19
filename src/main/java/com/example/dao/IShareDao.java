package com.example.dao;

import java.util.List;

import com.example.model.Orders;

public interface IShareDao {
	
	public int getOrderId();
	public List<Orders> findAll();
}
