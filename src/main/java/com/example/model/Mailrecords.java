package com.example.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the "Mailrecords" database table.
 * 
 */
@Entity
@Table(name = "Mailrecords")
public class Mailrecords implements Serializable {
	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer numone;

	private Integer numtwo;

	private Integer summary;
	
	private String email;
	
	private String content;
	
	private Timestamp time;
	
	public Mailrecords() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumone() {
		return numone;
	}

	public void setNumone(Integer numone) {
		this.numone = numone;
	}

	public Integer getNumtwo() {
		return numtwo;
	}

	public void setNumtwo(Integer numtwo) {
		this.numtwo = numtwo;
	}

	public Integer getSummary() {
		return summary;
	}

	public void setSummary(Integer summary) {
		this.summary = summary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
}