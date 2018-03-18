package com.example.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.IClientDao;
import com.example.model.Orders;
import com.example.model.Products;

@Service("clientService")
public class ClientServiceImpl implements IClientService {
  
	@Autowired
	private IClientDao clientDao;
	
	@Override
	@Transactional
	public void init() {
		// TODO Auto-generated method stub
		clientDao.initProduct(new Products("aaa", 25, 24, true, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
		clientDao.initProduct(new Products("bbb", 70, 5, false, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
		clientDao.initProduct(new Products("ccc", 10, 10, true, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
		
		clientDao.initOrder(new Orders(1, "aaa", 3, 25, 75, new Timestamp(System.currentTimeMillis())));
	}
	
	@Override
	@Transactional
	public List<Products> getOnSaleProductsList() {
		System.out.println("ClientServiceImpl getOnSaleProductsList");
		return clientDao.getOnSaleProductsList();
	}
	
	@Override
	public boolean getProductExist(String productName) {
		return clientDao.getProductExist(productName);
	}
	
	@Override
	public int getProductQuantity(String productName) {
		return clientDao.getProductQuantity(productName);
	}
	
	@Override
	public int getOrderId() {
		return clientDao.getOrderId();
	}
	
	@Override
	public int getProductPrice(String productName) {
		return clientDao.getProductPrice(productName);
	}
	
	@Override
	@Transactional
	public void orderProduct(int orderId, Products[] products) {
		
		for (int i = 0; i < products.length; i++) {
			Products product = new Products();
			product = clientDao.getProductInfo(products[i].getProductName());
			
			Orders orderProduct = new Orders();
			orderProduct.setOrderId(orderId);
			
			orderProduct.setProductName(products[i].getProductName());
			orderProduct.setQuantity(products[i].getQuantity());
			orderProduct.setPrice(product.getPrice());
			orderProduct.setTotal(products[i].getQuantity() * product.getPrice());
			orderProduct.setInsertTime(new Timestamp(System.currentTimeMillis()));
			
			clientDao.orderProducts(orderProduct);
			
			product.setQuantity(product.getQuantity() - products[i].getQuantity());
			clientDao.updateProductQuantity(product);
		}
	}
	
}
