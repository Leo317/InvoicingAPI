package com.example.dao;

import java.util.List;

import com.example.model.Products;

public interface IClientDao {
	
	public void init(Products product);
	public List<Products> getOnSaleProudctsList();
}
