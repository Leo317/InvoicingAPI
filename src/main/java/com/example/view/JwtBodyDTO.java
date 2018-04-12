package com.example.view;

import java.sql.Timestamp;

public class JwtBodyDTO {

	String jti;
	String sub;
	Timestamp iat;
	
	public String getJti() {
		return jti;
	}
	public String getSub() {
		return sub;
	}
	public Timestamp getIat() {
		return iat;
	}
	public void setJti(String jti) {
		this.jti = jti;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public void setIat(Timestamp iat) {
		this.iat = iat;
	}
}
