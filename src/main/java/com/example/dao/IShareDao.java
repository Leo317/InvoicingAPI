package com.example.dao;

import java.util.List;

import com.example.model.Orders;

public interface IShareDao {
	
	public List<Orders> findAll();
	public List<Orders> findOne(int id);
}
