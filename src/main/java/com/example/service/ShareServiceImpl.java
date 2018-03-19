package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.IShareDao;
import com.example.model.Orders;
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
			if (exist) {
				String tempStr = result.get(resultIndex).getOrderContent();
				tempStr += "ӆُƷ����Q : " + ordersList.get(i).getProductName()
						+ ", ��헃r�X : " + ordersList.get(i).getPrice()
						+ ", ӆُ���� : " + ordersList.get(i).getQuantity()
						+ ", ��Ӌ : " + ordersList.get(i).getPrice() * ordersList.get(i).getQuantity()
						+ "\n";
				result.get(resultIndex).setOrderContent(tempStr);
			} else {
				OrdersDTO temp = new OrdersDTO();
				temp.setOrderId(ordersList.get(i).getOrderId());
				temp.setOrderContent("ӆُƷ����Q : " + ordersList.get(i).getProductName()
						+ ", ��헃r�X : " + ordersList.get(i).getPrice()
						+ ", ӆُ���� : " + ordersList.get(i).getQuantity()
						+ ", ��Ӌ : " + ordersList.get(i).getPrice() * ordersList.get(i).getQuantity()
						+ "\n");
				result.add(temp);
			}
		}
		return result;
	}

	@Override
	@Transactional
	public OrdersDTO findOne(int id) {
		List<Orders> ordersList = shareDao.findOne(id);
		String tempStr = "";
		OrdersDTO result = new OrdersDTO();
		
		result.setOrderId(id);
		
		for (int i = 0; i < ordersList.size(); i++) {
			tempStr += "ӆُƷ����Q : " + ordersList.get(i).getProductName()
					+ ", ��헃r�X : " + ordersList.get(i).getPrice()
					+ ", ӆُ���� : " + ordersList.get(i).getQuantity()
					+ ", ��Ӌ : " + ordersList.get(i).getPrice() * ordersList.get(i).getQuantity()
					+ "\n";
		}
		result.setOrderContent(tempStr);
		return result;
	}

}
