package com.example.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.dao.ClientDao;
import com.example.model.Orders;
import com.example.model.Products;

@Repository("clientDao")
public class ClientDaoImpl implements ClientDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Products> getOrderableProductsList() {
		return this.sessionFactory.getCurrentSession().createQuery(" from Products where auction=true order by create_time ASC ").list();
	}

	@Override
	public boolean getProductExist(String productName) {
        Long count = (Long)this.sessionFactory.openSession().createQuery
        		("select count(*) from Products where product_name=:productName and auction=true")
        		.setParameter("productName", productName)
        		.uniqueResult();
        this.sessionFactory.openSession().close();
        return count > 0? true: false;
	}
	
	@Override
	public int getProductQuantity(String productName) {
        Query query = this.sessionFactory.openSession().createQuery
        		("select MAX(quantity) from Products where "
        				+ "product_name=:productName and auction=true").setParameter("productName", productName);
        this.sessionFactory.openSession().close();
        return (int)query.list().get(0);
	}
	
	@Override
	public int getOrderId() {
        Query query = this.sessionFactory.openSession().createQuery("select max(orderId) from Orders ");
        this.sessionFactory.openSession().close();
        return ((query.list().toString().equals("[null]"))) ?0 :(int)query.list().get(0);
	}
	
	@Override
	public void orderProducts(Orders order) {
		this.sessionFactory.getCurrentSession().save(order);
	}
	
	@Override
	public Products getProductInfo(String productName) {
		return (Products) this.sessionFactory.getCurrentSession().createQuery(
				"from Products where productName = :productName")
				.setParameter("productName", productName).uniqueResult();
	}
	
	@Override
	public void updateProductQuantity(Products product) {
		this.sessionFactory.getCurrentSession().update(product);
	}

}
