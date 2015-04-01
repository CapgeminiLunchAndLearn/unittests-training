package com.programmaniaks.training.unittest.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String username;
	private String password;
	private String name;
	private Date dateOfBirth;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.id, this.name, this.username, this.password, this.name, this.dateOfBirth);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (getClass() != obj.getClass())
			return false;
		final User other = (User) obj;
		return Objects.equal(this.id, other.id)
				&& Objects.equal(this.name, other.name)
				&& Objects.equal(this.username, other.username)
				&& Objects.equal(this.password, other.password)
				&& Objects.equal(this.name, other.name)
				&& Objects.equal(this.dateOfBirth, other.dateOfBirth);
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.addValue(this.id)
				.addValue(this.name)
				.addValue(this.username)
				.addValue(this.password)
				.addValue(this.name)
				.addValue(this.dateOfBirth)
				.toString();
	}

}
