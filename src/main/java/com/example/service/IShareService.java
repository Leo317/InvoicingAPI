package com.example.service;

import java.util.List;

import com.example.model.Orders;
import com.example.view.OrdersDTO;

public interface IShareService {
	
	public List<OrdersDTO> findAll();
}
