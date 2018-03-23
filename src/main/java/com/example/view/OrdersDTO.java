package com.example.view;

import java.util.List;

public class OrdersDTO {
	private int orderId;
	private List<CommodityDTO> commodity;

	public OrdersDTO() {
		
	}

	public OrdersDTO(int orderId, List<CommodityDTO> commodity) {
		this.orderId = orderId;
		this.commodity = commodity;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public List<CommodityDTO> getCommodity() {
		return commodity;
	}

	public void setCommodity(List<CommodityDTO> commodity) {
		this.commodity = commodity;
	}

}
