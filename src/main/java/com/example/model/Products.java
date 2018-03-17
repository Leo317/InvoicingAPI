package com.example.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Products")
public class Products implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer proudctId;

	private String proudctName;

	private Integer price;

	private Integer quantity;
	
	private boolean auction;

	private Timestamp insertTime;
	
	private Timestamp updateTime;
	
	public Products() {
	}
	
	public Products(String proudctName, Integer price, Integer quantity, boolean auction, Timestamp insertTime, Timestamp updateTime) {
		this.proudctName = proudctName;
		this.price = price;
		this.quantity = quantity;
		this.auction = auction;
		this.insertTime = insertTime;
		this.updateTime = updateTime;
	}

	public int getProudctId() {
		return proudctId;
	}

	public void setProudctId(Integer proudctId) {
		this.proudctId = proudctId;
	}

	public String getProudctName() {
		return proudctName;
	}

	public void setProudctName(String proudctName) {
		this.proudctName = proudctName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public boolean isAuction() {
		return auction;
	}

	public void setAuction(boolean auction) {
		this.auction = auction;
	}

	public Timestamp getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}