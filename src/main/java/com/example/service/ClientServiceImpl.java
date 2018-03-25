package com.example.service;

import java.sql.Timestamp;
import java.util.ArrayList;
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
	public List<Products> getOrderableProductsList() {
		return clientDao.getOrderableProductsList();
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
	public void orderProduct(int orderId, List<Products> products) {
		boolean exist = false;
		int resultIndex = 0;
		ArrayList<Products> result = new ArrayList<>();
		
		for (Products temp : products) {
			exist = false;
			resultIndex = 0;
			if (result.isEmpty()) {
				result.add(temp);
			} else {
				for (Products index : result) {
					if (index.getProductName().equals(temp.getProductName())) {
						result.get(resultIndex).setQuantity(index.getQuantity() + temp.getQuantity());
						exist = true;
					}
					resultIndex++;
				}
				if (!exist) {
					result.add(temp);
				}
			}
		}
		
		for (int i = 0; i < result.size(); i++) {
			Products product = new Products();
			product = clientDao.getProductInfo(result.get(i).getProductName());
			
			Orders orderProduct = new Orders();
			orderProduct.setOrderId(orderId);
			orderProduct.setProductName(result.get(i).getProductName());
			orderProduct.setQuantity(result.get(i).getQuantity());
			orderProduct.setCreateTime(new Timestamp(System.currentTimeMillis()));
			
			clientDao.orderProducts(orderProduct);
			
			product.setQuantity(product.getQuantity() - result.get(i).getQuantity());
			clientDao.updateProductQuantity(product);
		}
	}
	
}
