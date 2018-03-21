package com.example.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.IShareDao;
import com.example.model.Orders;
import com.example.view.CommodityDTO;
import com.example.view.OrdersDTO;

@Service("shareService")
public class ShareServiceImpl implements IShareService {
  
	@Autowired
	private IShareDao shareDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ShareServiceImpl.class);
	
	@Override
	@Transactional
	public List<OrdersDTO> findAll() {
		List<Orders> ordersList = shareDao.findAll();
		List<OrdersDTO> result = new ArrayList<OrdersDTO>();
		boolean exist = false;
		int resultIndex = 0;
		
		for (int i = 0; i < ordersList.size(); i++) {
			for (int j = 0; j < result.size(); j++) {
				if (ordersList.get(i).getOrderId() != result.get(j).getOrderId()) {
					exist = false;
				} else {
					resultIndex = j;
					exist = true;
					break;
				}
			}
			
			shareDao.updateProductPrice(ordersList.get(i).getProductName(), shareDao.getProductPrice(ordersList.get(i).getProductName()));
			
			if (exist) {
				List<CommodityDTO> list = result.get(resultIndex).getCommodity();
				
				CommodityDTO commodity = new CommodityDTO();
				commodity.setProductName(ordersList.get(i).getProductName());
				commodity.setPrice(shareDao.getProductPrice(ordersList.get(i).getProductName()));
				commodity.setQuantity(ordersList.get(i).getQuantity());
				commodity.setTotal(shareDao.getProductPrice(ordersList.get(i).getProductName()) * ordersList.get(i).getQuantity());
				commodity.setInsertTime(new Timestamp(System.currentTimeMillis()));
				list.add(commodity);
				
				result.get(resultIndex).setCommodity(list);
			} else {
				OrdersDTO temp = new OrdersDTO();
				temp.setOrderId(ordersList.get(i).getOrderId());
				
				List<CommodityDTO> list = new ArrayList<>();
				CommodityDTO commodity = new CommodityDTO();
				commodity.setProductName(ordersList.get(i).getProductName());
				commodity.setPrice(shareDao.getProductPrice(ordersList.get(i).getProductName()));
				commodity.setQuantity(ordersList.get(i).getQuantity());
				commodity.setTotal(shareDao.getProductPrice(ordersList.get(i).getProductName()) * ordersList.get(i).getQuantity());
				commodity.setInsertTime(new Timestamp(System.currentTimeMillis()));
				list.add(commodity);
				
				temp.setCommodity(list);
				result.add(temp);
			}
		}
		return result;
	}

	@Override
	@Transactional
	public List<OrdersDTO> findOne(int id) {
		List<Orders> ordersList = shareDao.findOne(id);
		List<OrdersDTO> result = new ArrayList<OrdersDTO>();
		
		OrdersDTO temp = new OrdersDTO();
		temp.setOrderId(id);
		List<CommodityDTO> list = new ArrayList<>();
		
		if (ordersList.size() > 0) {
			for (int i = 0; i < ordersList.size(); i++) {
				CommodityDTO commodity = new CommodityDTO();
				commodity.setProductName(ordersList.get(i).getProductName());
				commodity.setPrice(shareDao.getProductPrice(ordersList.get(i).getProductName()));
				commodity.setQuantity(ordersList.get(i).getQuantity());
				commodity.setTotal(shareDao.getProductPrice(ordersList.get(i).getProductName()) * ordersList.get(i).getQuantity());
				commodity.setInsertTime(new Timestamp(System.currentTimeMillis()));
				list.add(commodity);
			}
			temp.setCommodity(list);
		} else {
			temp.setCommodity(null);
		}
		result.add(temp);
		return result;
	}

}
