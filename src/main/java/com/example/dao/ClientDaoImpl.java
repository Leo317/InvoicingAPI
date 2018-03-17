package com.example.dao;

import java.util.List;

import org.hibernate.Query;
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
	public List<Products> getOnSaleProductsList() {
		return this.sessionFactory.getCurrentSession().createQuery(" from Products where auction=true ").list();
	}

	@Override
	public boolean getProductExist(String productName) {
//		String hql = "select count(*) from Products p where p.productName=" + productName + " and auction=true";
//		System.out.println(hql);
//        Query query = this.sessionFactory.openSession().createQuery(hql);
//        System.out.println(query);
//        Long count =  (Long)query.uniqueResult();
//        this.sessionFactory.openSession().close();
        
        Long count = (Long)this.sessionFactory.openSession().createQuery("select count(*) from Products where product_name='" + productName + "' and auction=true").uniqueResult();
        this.sessionFactory.openSession().close();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + count);
        return count > 0? true: false;
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
