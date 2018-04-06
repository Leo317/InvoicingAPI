package com.example.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Roles")
public class Roles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3933294425716961331L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;	

	private String roleName;

	public Integer getId() {
		return id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
