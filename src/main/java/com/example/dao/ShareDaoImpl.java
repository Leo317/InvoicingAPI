package com.example.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.Orders;

@Repository("shareDao")
public class ShareDaoImpl implements IShareDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> findAll() {
		return this.sessionFactory.getCurrentSession().createQuery(" from Orders order by insert_time ASC ").list();
	}
	
	@Override
	public int getProductPrice(String productName) {
        Query query = this.sessionFactory.openSession().createQuery("select MAX(price) from Products "
        		+ "where product_name=:productName").setParameter("productName", productName);
        this.sessionFactory.openSession().close();
        return (int)query.list().get(0);
	}
	
	public void updateProductPrice(String productName, int price) {
		this.sessionFactory.getCurrentSession().createQuery("update Orders set price=:price where product_name=:productName")
		.setParameter("price", price)
		.setParameter("productName", productName);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> findOne(int id) {
		return this.sessionFactory.getCurrentSession().createQuery(" from Orders where order_id = :id ").setInteger("id", id).list();
	}
}
