package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.IMailrecordsDao;
import com.example.model.Mailrecords;

@Service("keywordFinderService")
@Transactional
public class KeywordFinder {
  
  @Autowired
  private IMailrecordsDao iMailRecordsDao;

  public List<Mailrecords> findAll() {
    return iMailRecordsDao.findAll();
  }

  public List<Mailrecords> findByKeyword(String keyword) {
    return iMailRecordsDao.findByKeyword(keyword);
  }
}

