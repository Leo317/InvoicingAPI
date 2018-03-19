package com.example.service;

import java.util.List;

import com.example.view.OrdersDTO;

public interface IShareService {
	
	public List<OrdersDTO> findAll();
	public OrdersDTO findOne(int id);
}
