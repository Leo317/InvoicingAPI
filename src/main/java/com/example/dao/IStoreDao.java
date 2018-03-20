package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Products;

@Repository
public interface IStoreDao extends CrudRepository<Products, String> {
  @Query(value = "select * from products p", nativeQuery = true)
  public List<Products> findAll();
  @Query(value = "select * from products p where p.product_name like %?1% "
  		+ "and p.auction = ?2 order by p.insert_time desc", 
  		nativeQuery = true)
  public List<Products> findByCond(String keyword, boolean auction);
  
  @Query(value = "select * from products where product_id = ?1",
		nativeQuery = true)
  public List<Products> productExisted(int productId);
  @Transactional
  @Modifying(clearAutomatically = true)
  @Query(value = "update products set product_name = :productName, "
  		+ "quantity = :quantity, auction = :auction where "
  		+ "product_id = :productId", 
	  	nativeQuery = true)
  public void updateProducts(@Param("productId") int productId, 
		  					 @Param("productName") String productName, 
		  					 @Param("quantity") int quantity, 
		  					 @Param("auction") boolean auction);
}