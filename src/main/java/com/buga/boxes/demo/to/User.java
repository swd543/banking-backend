package com.buga.boxes.demo.to;

import java.sql.Date;
import java.util.UUID;

public class User {
	private UUID id;
	private String name;
	private String gender;
	private String email;
	private Date dateOfJoining;
	private Integer age;
	
	public User() {	}
	
	public User(String name, String gender, String email, Date dateOfJoining, Integer age) {
		super();
		this.id = UUID.randomUUID();
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.dateOfJoining = dateOfJoining;
		this.age = age;
	}
	
	public User(UUID id, String name, String gender, String email, Date dateOfJoining, Integer age) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.dateOfJoining = dateOfJoining;
		this.age = age;
	}

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", gender=" + gender + ", email=" + email + ", dateOfJoining="
				+ dateOfJoining + ", age=" + age + "]";
	}

}
