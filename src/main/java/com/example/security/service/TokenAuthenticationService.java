package com.example.security.service;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.example.view.JwtBodyDTO;
import com.google.gson.Gson;

import io.jsonwebtoken.Jwts;

public class TokenAuthenticationService {

	public static final long EXPIRATIONTIME = 864_000_000; // 10 days
	public static final String SECRET = "Xmx128hahaha";
	public static final String HEADER_STRING = "Authorization";
	public static final String ISSUER = "Wistron";
	public static final String SUBJECT = "InvoicingAPI";

//	public static void addAuthentication(HttpServletResponse res, String userId) {
//		String jwtToken = createJwt(userId, ISSUER, SUBJECT, EXPIRATIONTIME);
//
//		res.addHeader(HEADER_STRING, jwtToken);
//	}
//	
//	public static String createJwt(String id, String issuer, String subject, long ttlMilliseconds) {
//
//		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
//		
//		long nowMillis = System.currentTimeMillis();
//		Date now = new Date(ttlMilliseconds);
//		
//		//We will sign our JWT with our API key: SECRET
//		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
//		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
//		
//		//Let's set the JWT Claims
//		JwtBuilder builder = 
//		  Jwts.builder().setId(id)
//		                .setIssuedAt(now)
//		                .setSubject(subject)
//				        .setIssuer(issuer)
//				        .signWith(signatureAlgorithm, signingKey);
//		//If it has been specified, let's add the expiration
//		if(ttlMilliseconds >= 0) {
//		    long expMillis = nowMillis + ttlMilliseconds;
//		    Date exp = new Date(expMillis);
//		    builder.setExpiration(exp);
//		}
//		
//		//Builds the JWT and serializes it to a compact, URL-safe string
//		return builder.compact();
//	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody()
					.getSubject();
			//String user = getToken(token).getSub();

			return user != null ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;
		}
		return null;
	}
	
    public static JwtBodyDTO getToken(String token) {
        String jwtToken = token;
        // Decode
        String[] split_string = jwtToken.split("\\.");
        String base64EncodedHeader = split_string[0];
        String base64EncodedBody = split_string[1];
        // String base64EncodedSignature = split_string[2];
        // JWT Header
        Base64 base64Url = new Base64(true);
        //String header = new String(base64Url.decode(base64EncodedHeader));
        // JWT Body
        String body = new String(base64Url.decode(base64EncodedBody));
		final Gson gson = new Gson();
        return gson.fromJson(body, JwtBodyDTO.class);
    }

	TokenAuthenticationService() {
	}
}
