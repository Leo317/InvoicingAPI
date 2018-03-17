package com.example.service;

import java.util.List;

import com.example.model.Products;

public interface IClientService {
	
//  public void createAll(List<Mailrecords> recordsList) throws MailrecordsTransactionException;
	public void init();
	public List<Products> getOnSaleProductsList();
	public boolean getProductExist(String productName);
}
