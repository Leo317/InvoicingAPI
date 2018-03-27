package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Products;

@Repository
public interface IStoreDao extends CrudRepository<Products, String> {
  @Query(value = "select * from products p order by p.create_time desc", 
		 nativeQuery = true)
  public List<Products> findAll();
  @Query(value = "select * from products p where p.product_name like %?1% "
  		+ "and p.auction = ?2 order by p.create_time desc", 
  		nativeQuery = true)
  public List<Products> findByCond(String keyword, boolean auction);
}