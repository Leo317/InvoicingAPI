package com.example.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
  
  public List<Products> findByKeyword(String keyword) {
    return Stream.of(iStoreDao.findByCond(keyword, true), 
      iStoreDao.findByCond(keyword, false))
      .flatMap(Collection::stream)
      .collect(Collectors.toList());
  }
}
