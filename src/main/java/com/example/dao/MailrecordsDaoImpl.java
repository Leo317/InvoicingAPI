package com.example.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.model.Mailrecords;

@Repository("mailRecordsDao")
public class MailrecordsDaoImpl implements IMailrecordsDao {
  @Autowired
  SessionFactory sessionFactory;

  @Override
  public void create(Mailrecords mailRecords) {
	  this.sessionFactory.getCurrentSession().save(mailRecords);		
  }

  @Override
  public List<Mailrecords> findAll() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    
    Criteria criteria = session.createCriteria(Mailrecords.class);
    @SuppressWarnings("unchecked")
	List<Mailrecords> recordList = criteria.list();
    Iterator<Mailrecords> iterator = recordList.iterator();
    
    List<Mailrecords> result = new ArrayList<Mailrecords>();
    while(iterator.hasNext()) {
      result.add(iterator.next());
    }
	return result;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Mailrecords> findByKeyword(String keyword) {
	  
    return (List<Mailrecords>) this.sessionFactory.getCurrentSession().createQuery(
    "from Mailrecords rec where rec.email like '%' || :keyword || '%' "
    + "or rec.content like '%' || :keyword || '%'").setString("keyword", keyword).list();
  }
}
