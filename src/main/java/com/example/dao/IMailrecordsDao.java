package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Mailrecords;
@Repository
public interface IMailrecordsDao extends CrudRepository<Mailrecords, String> {
  @Query(value = "select * from mailrecords mailrecords", nativeQuery = true)
  public List<Mailrecords> findAll();
  @Query(value = "select * from mailrecords mailrecords where mailrecords.email like %?1% or mailrecords.content like %?1% order by mailrecords.time desc", nativeQuery = true)
  public List<Mailrecords> findByKeyword(String keyword);
}
