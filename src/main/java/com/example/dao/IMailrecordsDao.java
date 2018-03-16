package com.example.dao;

import java.util.List;

import com.example.model.Mailrecords;

public interface IMailrecordsDao {
  public void create(Mailrecords mailRecords);
  public List<Mailrecords> findAll();
  //@Query("select * from Mailrecords mailrecords where mailrecords.email like %?1% or mailrecords.content like %?1%")
  public List<Mailrecords> findByKeyword(String keyword);
}
