package com.prateek.learnspring.model;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@SqlResultSetMappings({
	@SqlResultSetMapping(name="UserSearchDtoMapping",
	classes = {
			@ConstructorResult(targetClass=UserSearch.class, 
					columns = {
							@ColumnResult(name="userId", type=String.class),
							@ColumnResult(name="firstName", type=String.class),
							@ColumnResult(name="lastName", type=String.class),
							@ColumnResult(name="email", type=String.class)
			})
	})
})
@Entity
@Table(name="UserDetails")
public class User {
	
	@Id @GeneratedValue(generator="uuid2")
	@GenericGenerator(name="uuid2", strategy="uuid2")
	private String id;
	
	@Column(unique=true)
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	public User(String email, String firstName, String lastName, String password) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}
	
	public User() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return email + " " + firstName + " " + lastName + " " + password;
	}
}
