package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Products;

@Repository
public interface IStoreDao extends CrudRepository<Products, String> {
  @Query(value = "select * from products", nativeQuery = true)
  public List<Products> findAll();
  @Query(value = "select * from products where products.productName like %?1% order by products.insertTime desc", nativeQuery = true)
  public List<Products> findByKeyword(String keyword);
}