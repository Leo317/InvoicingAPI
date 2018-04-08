package com.example.security.service;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.example.security.model.RoleNames;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	public static final long EXPIRATIONTIME = 864_000_000; // 10 days
	public static final String SECRET = "ThisIsASecret";
	public static final String HEADER_STRING = "Authorization";
	public static final String TOKEN_PREFIX = "Wistron";
	static String userNameWithToken = "";

	public static void addAuthentication(HttpServletResponse res, String username) {
		String jwtPrefix = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();

		userNameWithToken = "";
		StringBuilder stringBuilder = new StringBuilder(userNameWithToken);
		if(username.compareTo("client") == 0) {
			userNameWithToken = stringBuilder
			.append(TOKEN_PREFIX)
			.append(" ")
			.append(RoleNames.CUSTOMER)
			.append(" ")
			.append(username).toString();
		} else if(username.compareTo("store") == 0) {
			userNameWithToken = stringBuilder
			.append(TOKEN_PREFIX)
			.append(" ")
			.append(RoleNames.STORE)
			.append(" ")
			.append(username).toString();			
		} else if(username.compareTo("share") == 0) {
			userNameWithToken = stringBuilder
			.append(TOKEN_PREFIX)
			.append(" ")
			.append(RoleNames.STORE)
			.append(" ")
			.append(username).toString();			
		}

		res.addHeader(HEADER_STRING, userNameWithToken + " " + jwtPrefix);
	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(userNameWithToken, "")).getBody()
					.getSubject();

			return user != null ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;
		}
		return null;
	}

	TokenAuthenticationService() {
	}
}
