package com.example.view;

public class SearchDTO implements Comparable<SearchDTO> {
	private String time;
	private String content;
	private String email;

	public SearchDTO() {
		
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public int compareTo(SearchDTO dto) {
		return getTime().compareTo(dto.getTime());
	} 
}
