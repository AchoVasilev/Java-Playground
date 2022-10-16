package com.example.PathFinder.models;

import com.example.PathFinder.models.enums.Level;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
	public User() {
		this.roles = new HashSet<>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(unique = true)
	private String email;

	@Column(name = "full_name")
	private String fullName;

	private int age;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;

	@Enumerated(EnumType.STRING)
	private Level level;

	public long getId() {
		return id;
	}

	public User setId(long id) {
		this.id = id;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public User setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public User setRoles(Set<Role> roles) {
		this.roles = roles;
		return this;
	}

	public String getFullName() {
		return fullName;
	}

	public User setFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}

	public int getAge() {
		return age;
	}

	public User setAge(int age) {
		this.age = age;
		return this;
	}

	public Level getLevel() {
		return level;
	}

	public User setLevel(Level level) {
		this.level = level;
		return this;
	}
}
