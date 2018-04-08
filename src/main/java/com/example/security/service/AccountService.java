package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.security.dao.IUserDao;
import com.example.security.model.AccountCredentials;

@Service("accountService")
@Transactional
public class AccountService {
  @Autowired
  private IUserDao iUserDao;
  
  public void insertAccount(AccountCredentials user) {
    iUserDao.save(user);
  }
}
