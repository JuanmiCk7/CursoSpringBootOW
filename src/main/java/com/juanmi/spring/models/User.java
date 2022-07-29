package com.juanmi.spring.models;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {

	@Id @GeneratedValue
	private long id;
	
	private String name;
	private String lastName;
	private String avatar;
	private String email;
	private String password;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date signUpDate;
	
	
	public User() {
		
	}
	
	public User(String name, String lastName, String avatar, String email, String password) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.avatar = avatar;
		this.email = email;
		this.password = password;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getAvatar() {
		return avatar;
	}


	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getSignUpDate() {
		return signUpDate;
	}


	public void setSignUpDate(Date signUpDate) {
		this.signUpDate = signUpDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(avatar, email, id, lastName, name, password, signUpDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(avatar, other.avatar) && Objects.equals(email, other.email) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(signUpDate, other.signUpDate);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + ", avatar=" + avatar + ", email="
				+ email + ", password=" + password + ", signUpDate=" + signUpDate + "]";
	}
	
	


	
	
}
