package com.example.security.service;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	public static final long EXPIRATIONTIME = 864_000_000; // 10 days
	public static final String SECRET = "Xmx128hahaha";
	public static final String HEADER_STRING = "Authorization";
	public static final String ISSUER = "Wistron";
	public static final String SUBJECT = "InvoicingAPI";

	public static void addAuthentication(HttpServletResponse res, String username) {
		String jwtToken = createJwt(username, ISSUER, SUBJECT, EXPIRATIONTIME);

		res.addHeader(HEADER_STRING, jwtToken);
	}
	
	public static String createJwt(String id, String issuer, String subject, long ttlMilliseconds) {

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
		
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(ttlMilliseconds);
		
		//We will sign our JWT with our API key: SECRET
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		
		//Let's set the JWT Claims
		JwtBuilder builder = 
		  Jwts.builder().setId(id)
		                .setIssuedAt(now)
		                .setSubject(subject)
				        .setIssuer(issuer)
				        .signWith(signatureAlgorithm, signingKey);
		//If it has been specified, let's add the expiration
		if(ttlMilliseconds >= 0) {
		    long expMillis = nowMillis + ttlMilliseconds;
		    Date exp = new Date(expMillis);
		    builder.setExpiration(exp);
		}
		
		//Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody()
					.getSubject();

			return user != null ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;
		}
		return null;
	}

	TokenAuthenticationService() {
	}
}
