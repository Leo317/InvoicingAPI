package com.example.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the "Summary" database table.
 * 
 */
@Entity
@Table(name = "Summary")
public class Summary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer numone;

	private Integer numtwo;

	private Integer summary;

	public Summary() {
	}
	
	public Summary(Integer no1, Integer no2) {
		setNumone(no1);
		setNumtwo(no2);
		setSummary(no1 + no2);
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumone() {
		return this.numone;
	}

	public void setNumone(Integer numone) {
		this.numone = numone;
	}

	public Integer getNumtwo() {
		return this.numtwo;
	}

	public void setNumtwo(Integer numtwo) {
		this.numtwo = numtwo;
	}

	public Integer getSummary() {
		return this.summary;
	}

	public void setSummary(Integer summary) {
		this.summary = summary;
	}

}