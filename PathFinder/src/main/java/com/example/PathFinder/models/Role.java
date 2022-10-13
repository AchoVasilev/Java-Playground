package com.example.PathFinder.models;

import com.example.PathFinder.models.enums.UserRoles;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Enumerated(EnumType.STRING)
	@Column(unique = true)
	private UserRoles name;

	public long getId() {
		return id;
	}

	public Role setId(long id) {
		this.id = id;
		return this;
	}

	public UserRoles getName() {
		return name;
	}

	public Role setName(UserRoles name) {
		this.name = name;
		return this;
	}
}
