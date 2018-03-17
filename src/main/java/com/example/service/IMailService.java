package com.example.service;

import java.util.List;

public interface IMailService {

  public void sendAndSaveMail(int num1, int num2, int total, List<String> emails);

}
