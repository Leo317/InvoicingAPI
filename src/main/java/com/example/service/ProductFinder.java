package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.IStoreDao;
import com.example.model.Products;

@Service("productFinderService")
@Transactional
public class ProductFinder {
  @Autowired
  private IStoreDao iStoreDao;
  
  public List<Products> findAll() {
	return iStoreDao.findAll();
  }

  public List<Products> findByCond(String keyword, boolean auction) {
	return iStoreDao.findByCond(keyword, auction);
  }
}
