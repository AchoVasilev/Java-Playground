package com.example.PathFinder.models;

import com.example.PathFinder.models.enums.Level;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "routes")
public class Route {
	public Route() {
		this.comments = new HashSet<>();
		this.categories = new HashSet<>();
		this.pictures = new HashSet<>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Lob
	private String gpxCoordinates;

	@Enumerated(EnumType.STRING)
	private Level level;

	private String name;

	@ManyToOne
	private User author;

	private String videoUrl;

	@Column(columnDefinition = "TEXT")
	private String description;

	@OneToMany(mappedBy = "route")
	private Set<Picture> pictures;

	@OneToMany(targetEntity = Comment.class, mappedBy = "route", cascade = CascadeType.ALL)
	private Set<Comment> comments;

	@ManyToMany
	private Set<Category> categories;

	public long getId() {
		return id;
	}

	public Route setId(long id) {
		this.id = id;
		return this;
	}

	public String getGpxCoordinates() {
		return gpxCoordinates;
	}

	public Route setGpxCoordinates(String gpxCoordinates) {
		this.gpxCoordinates = gpxCoordinates;
		return this;
	}

	public Level getLevel() {
		return level;
	}

	public Route setLevel(Level level) {
		this.level = level;
		return this;
	}

	public String getName() {
		return name;
	}

	public Route setName(String name) {
		this.name = name;
		return this;
	}

	public User getAuthor() {
		return author;
	}

	public Route setAuthor(User author) {
		this.author = author;
		return this;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public Route setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
		return this;
	}

	public Set<Picture> getPictures() {
		return pictures;
	}

	public Route setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
		return this;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public Route setComments(Set<Comment> comments) {
		this.comments = comments;
		return this;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public Route setCategories(Set<Category> categories) {
		this.categories = categories;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public Route setDescription(String description) {
		this.description = description;
		return this;
	}
}
