package com.example.service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class CommonTools {
	
	private static final int LENGTH_PRODUCTNAME = 255;
	
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
	
	public static boolean productNameLengthCheck(String productName) {
	  if(productName.length() > LENGTH_PRODUCTNAME) {
	    return false;
	  } else {
	    return true;
	  }
    }
}
