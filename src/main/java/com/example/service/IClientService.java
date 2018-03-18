package com.example.service;

import java.util.List;

import com.example.model.Products;

public interface IClientService {
	
//  public void createAll(List<Mailrecords> recordsList) throws MailrecordsTransactionException;
	public void init();
	public List<Products> getOnSaleProductsList();
	public boolean getProductExist(String productName);
	public int getProductQuantity(String productName);
	public int getOrderId();
	public int getProductPrice(String productName);
	
	public void orderProduct(int orderId, String productName, int quantity, int price, int remaining);
}
