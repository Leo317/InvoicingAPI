package com.example.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.Products;

@Repository("clientDao")
public class ClientDaoImpl implements IClientDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void init(Products product) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().save(product);
	}
	
	@Override
	public List<Products> getOnSaleProudctsList() {
		return this.sessionFactory.getCurrentSession().createQuery(" from Products where auction=true ").list();
	}

	
	
	

//  @Override
//  public void create(Mailrecords mailRecords) {
//	  this.sessionFactory.getCurrentSession().save(mailRecords);		
//  }
//
//  @Override
//  public List<Mailrecords> findAll() {
//    Session session = sessionFactory.openSession();
//    session.beginTransaction();
//    
//    Criteria criteria = session.createCriteria(Mailrecords.class);
//    @SuppressWarnings("unchecked")
//	List<Mailrecords> recordList = criteria.list();
//    Iterator<Mailrecords> iterator = recordList.iterator();
//    
//    List<Mailrecords> result = new ArrayList<Mailrecords>();
//    while(iterator.hasNext()) {
//      result.add(iterator.next());
//    }
//	return result;
//  }
//
//  @SuppressWarnings("unchecked")
//  @Override
//  public List<Mailrecords> findByKeyword(String keyword) {
//	  
//    return (List<Mailrecords>) this.sessionFactory.getCurrentSession().createQuery(
//    "from Mailrecords rec where rec.email like '%' || :keyword || '%' "
//    + "or rec.content like '%' || :keyword || '%'").setString("keyword", keyword).list();
//  }
}
