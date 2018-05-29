package com.example.demo.user;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "user_table")
@ApiModel
public class User {

	@Id
	@GeneratedValue
	private int id;

	@Size(min = 2, message = "name should have at least 2 characters")
	@ApiModelProperty(notes = "name sholud be mininum 2 characters")
	private String name;

	@Past
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(notes = "date of birth should be in past")
	private Date birthdate;

	public User() {

	}

	public User(String name, Date birthdate) {
		super();
		this.name = name;
		this.birthdate = birthdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, birthdate=%s]", id, name, birthdate);
	}

}
