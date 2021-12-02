package com.olx.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADVERTISE")
public class AdvertiseEntity {
	
	@Id
	@GeneratedValue
	private int id;
	@Column(name = "advertise_title")
	private String title;
	@Column(name = "advertise_price")
	private int price;
	@Column(name = "advertise_category_id")
	private Integer categoryId;
	@Column(name = "advertise_category")
	private String category;
	@Column(name = "advertise_description")
	private String description;
	@Column(name = "advertise_username")
	private String username;
	@Column(name = "advertise_createdDate")
	private LocalDate createdDate;
	@Column(name = "advertise_modifiedDate")
	private LocalDate modifiedDate;
	@Column(name = "advertise_status")
	private String status;
	@Column(name = "advertise_active")
	private int active;
	@Column(name = "advertise_posted_by")
	private String postedBy;
	
	public AdvertiseEntity() {}

	public AdvertiseEntity(int id, String title, int price, int categoryId, String category, String description, String username,
			LocalDate createdDate, LocalDate modifiedDate, String status, int active, String postedBy) {
		super();
		this.id = id;
		this.title = title;
		this.price = price;
		this.categoryId = categoryId;
		this.category = category;
		this.description = description;
		this.username = username;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.status = status;
		this.active = active;
		this.postedBy = postedBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}


	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	@Override
	public String toString() {
		return "AdvertiseEntity [id=" + id + ", title=" + title + ", price=" + price + ", category=" + category
				+ ", description=" + description + ", username=" + username + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", status=" + status + ", active=" + active + ", postedBy="
				+ postedBy + "]";
	}
	
}
