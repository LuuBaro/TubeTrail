package com.asm.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "[Video]")
public class Video {
	@Id
	@Column(name = "Id", nullable = false)
	private String id;

	@Column(name = "Title", nullable = false)
	private String title;

	@Column(name = "Poster")
	private String poster;

	@Column(name = "Views")
	private Integer views;

	@Column(name = "Description")
	private String description;

	@Column(name = "Active", nullable = false)
	private boolean active;

	@OneToMany(mappedBy = "video")
	private List<Favorite> favorites;

	@OneToMany(mappedBy = "video")
	private List<Share> shares;

	public Video() {
		// TODO Auto-generated constructor stub
	}

	public Video(String id, String title, int views, boolean active, String description, String poster) {
		this.id = id;
		this.title = title;
		this.views = views;
		this.active = active;
		this.description = description;
		this.poster = poster;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public boolean isLikedByUser(User user) {
	    if (favorites == null) {
	        return false;
	    }
	    return favorites.stream()
	                    .anyMatch(favorite -> favorite.getUser().equals(user));
	}

	
	public List<Share> getShares() {
		return shares;
	}

	public void setShares(List<Share> shares) {
		this.shares = shares;
	}

}
