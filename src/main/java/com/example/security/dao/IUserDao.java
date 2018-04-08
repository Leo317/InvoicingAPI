package com.example.security.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.security.model.AccountCredentials;

public interface IUserDao extends CrudRepository<AccountCredentials, String> { 

}
