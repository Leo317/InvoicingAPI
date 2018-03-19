package com.example.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Products;

@Repository
public interface IStoreDao extends CrudRepository<Products, String> {
  @Query(value = "select * from products p", nativeQuery = true)
  public List<Products> findAll();
  @Query(value = "select * from products p where p.product_name like %?1% "
  		+ "and p.auction = ?2 order by p.insert_time desc", 
  		nativeQuery = true)
  public List<Products> findByCond(String keyword, boolean auction);
  @Query(value = "insert into products (product_name, price, quantity, auction, "
  		+ "insert_time, update_time) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", 
  		nativeQuery = true)
  public void insertProducts(String productName, int price, int quantity,
    boolean auction, Timestamp insertTime, Timestamp updateTime);
  @Modifying
  @Query(value = "update products p set p.product_name=?1 and p.quantity=?2 and "
  		+ "p.auction=?3", 
	  	nativeQuery = true)
  public void updateProducts(String productName, int quantity, boolean auction);
}