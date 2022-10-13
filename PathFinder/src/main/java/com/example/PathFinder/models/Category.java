package com.example.PathFinder.models;

import com.example.PathFinder.models.enums.RouteCategory;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private RouteCategory name;

	private String description;

	public long getId() {
		return id;
	}

	public Category setId(long id) {
		this.id = id;
		return this;
	}

	public RouteCategory getName() {
		return name;
	}

	public Category setName(RouteCategory name) {
		this.name = name;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Category setDescription(String description) {
		this.description = description;
		return this;
	}
}
