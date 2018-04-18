package com.example.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User_roles")
public class User_roles implements Serializable {
	private static final long serialVersionUID = 4L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer user_role_id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="role")
	private String role;

}