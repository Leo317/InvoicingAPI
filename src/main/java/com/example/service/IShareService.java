package com.example.service;

import java.util.List;

import com.example.view.OrdersDTO;

public interface IShareService {
	
	public List<OrdersDTO> findAll();
	public List<OrdersDTO> findOne(int id);
}
