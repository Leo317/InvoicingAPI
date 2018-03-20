package com.example.dao;

import java.util.List;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Orders> findOne(int id) {
		return this.sessionFactory.getCurrentSession().createQuery(" from Orders where order_id = :id ").setInteger("id", id).list();
	}
}
