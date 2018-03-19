package com.example.service;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class CommonTools {
	
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
}
