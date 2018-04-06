package com.example.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Orders")
public class Orders implements Serializable {
	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="order_id")
	private Integer orderId;
	
	private String productName;

	private Integer quantity;
	
	@Column(name="create_time")
	private Timestamp createTime;
	
	private String roleName;

	public Integer getId() {
		return id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public String getProductName() {
		return productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}