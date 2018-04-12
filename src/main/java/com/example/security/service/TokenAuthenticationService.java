package com.example.security.service;

import java.io.UnsupportedEncodingException;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.view.JwtBodyDTO;
import com.google.gson.Gson;

public class TokenAuthenticationService {

	public static final long EXPIRATIONTIME = 864_000_000; // 10 days
	public static final String SECRET = "Xmx128hahaha";
	public static final String HEADER_STRING = "Authorization";
	public static final String ISSUER = "Wistron";
	public static final String SUBJECT = "InvoicingAPI";

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {

			String userId = getUserIdFromToken(token);
			if((userId != null) && (userId.isEmpty())) {
				userId = null;
			}

			return userId != null ? new UsernamePasswordAuthenticationToken(userId, null, Collections.emptyList()) : null;
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
	
    public static JwtBodyDTO getToken(String token) {
        String jwtToken = token;
        // Decode
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedBody = split_string[1];
        // JWT Header
        Base64 base64Url = new Base64(true);
        // JWT Body
        String body = new String(base64Url.decode(base64EncodedBody));
		final Gson gson = new Gson();
        return gson.fromJson(body, JwtBodyDTO.class);
    }

	TokenAuthenticationService() {
	}
}
