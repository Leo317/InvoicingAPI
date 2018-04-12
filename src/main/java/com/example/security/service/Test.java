package com.example.security.service;

import java.util.Date;

import org.apache.tomcat.util.codec.binary.Base64;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Test {
	
	public static final long EXPIRATIONTIME = 864_000_000; // 10 days
	public static final String SECRET = "Xmx128hahaha";
	public static final String HEADER_STRING = "Authorization";
	public static final String ISSUER = "Wistron";
	public static final String SUBJECT = "InvoicingAPI";

	public static void main(String[] args) {
		String token = genAuthenticationJWT("");
	    System.out.println(token);
		String decodeString = getToken(token);
		System.out.println(decodeString);
	}
	
	public static String genAuthenticationJWT(String token) {
		Date now = new Date();
		String jwt = Jwts.builder().setId(token).setSubject(token).setIssuedAt(now)
				.signWith(SignatureAlgorithm.HS256, SECRET).compact();
		return jwt;
	}
	
    public static String getToken(String token) {
        String jwtToken = token;
        // Decode
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedHeader = split_string[0];
        String base64EncodedBody = split_string[1];
        // String base64EncodedSignature = split_string[2];
        // JWT Header
        Base64 base64Url = new Base64(true);
        String header = new String(base64Url.decode(base64EncodedHeader));
        // JWT Body
        String body = new String(base64Url.decode(base64EncodedBody));
        StringBuilder response = new StringBuilder(header);
        return response.append(",").append(body)
                .toString();
    }


}
