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

	@Override
	public int getOrderId() {
		Query query = this.sessionFactory.openSession().createQuery("select max(orderId) from Orders ");
        this.sessionFactory.openSession().close();
        return (int)query.list().get(0);
	}
	
	@Override
	public List<Orders> findAll() {
		return this.sessionFactory.getCurrentSession().createQuery(" from Orders order by insert_time ASC ").list();
	}

}
