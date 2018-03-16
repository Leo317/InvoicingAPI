package com.example.view;

import java.util.List;

public class TestDTO {
	
	private String id1;
	private String id2;
	private List<String> emails;
	
	public TestDTO(String id1, String id2, List<String> emails) {
		this.id1 = id1;
		this.id2 = id2;
		this.emails = emails;
	}
	
	public TestDTO() {
		
	}

	public String getId1() {
		return id1;
	}

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	public List<String> getEmails() {
		return emails;
	}

	public void setEmails(List<String> emails) {
		this.emails = emails;
	}
}
