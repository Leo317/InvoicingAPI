package com.example.security.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class TokenAuthenticationService {

	public static final long EXPIRATIONTIME = 864_000_000; // 10 days
	public static final String SECRET = "Xmx128hahaha";
	public static final String HEADER_STRING = "Authorization";
	public static final String ISSUER = "Wistron";
	public static final String SUBJECT = "InvoicingAPI";

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {

			User principal = parseUserFromToken(token);

			System.out.println(principal.getAuthorities());
			return principal != null ? 
			    new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities()) : null;
		}
		return null;
	}
	
    public static String getUserIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("userid").asString();
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
            //log WRONG Encoding message
            return null;
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            //log Token Verification Failed
            return null;
        }
    }
    
    public static String getRolesFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim("roles").asString();
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
            //log WRONG Encoding message
            return null;
        } catch (JWTVerificationException exception) {
            exception.printStackTrace();
            //log Token Verification Failed
            return null;
        }
    }
    
	public static User parseUserFromToken(String token) {

		String userId = getUserIdFromToken(token);
		String roleString = getRolesFromToken(token);
		List<SimpleGrantedAuthority> roles = new ArrayList<>();
		if (!StringUtils.isEmpty(roleString)) {
			String[] roleValues = StringUtils.split(roleString, ",");
			for (String roleValue : roleValues) {
				roles.add(new SimpleGrantedAuthority("ROLE_" + roleValue));
			}
		}
		
		return new User(userId, token, roles);
	}

	TokenAuthenticationService() {
	}
}
