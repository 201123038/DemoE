package com.mitocode.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tableconsult")
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "latino")
	private boolean latino;

	@Column(name = "text", length = 100)
	private String text;
	
	@Column(name = "sortby")
	private int sortby;

	@Column(name = "news")
	private boolean news;
	
	@Column(name = "blogs")
	private boolean blogs;
	
	@Column(name = "discussions")
	private boolean discussions;
	
	@Column(name = "reviews")
	private boolean reviews;
	
	@Column(name = "language", length = 100)
	private String language;
	
	@Column(name = "country", length = 100)
	private String country;
	
	@Column(name = "location", length = 100)
	private String location;
	
	@Column(name = "category")
	private int category;
	
	@Column(name = "date", length = 100)
	private String date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isLatino() {
		return latino;
	}

	public void setLatino(boolean latino) {
		this.latino = latino;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getSortby() {
		return sortby;
	}

	public void setSortby(int sortby) {
		this.sortby = sortby;
	}

	public boolean isNews() {
		return news;
	}

	public void setNews(boolean news) {
		this.news = news;
	}

	public boolean isBlogs() {
		return blogs;
	}

	public void setBlogs(boolean blogs) {
		this.blogs = blogs;
	}

	public boolean isDiscussions() {
		return discussions;
	}

	public void setDiscussions(boolean discussions) {
		this.discussions = discussions;
	}

	public boolean isReviews() {
		return reviews;
	}

	public void setReviews(boolean reviews) {
		this.reviews = reviews;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


}
