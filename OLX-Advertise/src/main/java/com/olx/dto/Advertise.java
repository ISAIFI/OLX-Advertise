package com.olx.dto;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Advertise Model")
public class Advertise {
	
	@ApiModelProperty("Unique Identifier of Advertise")
	private int id;
	@ApiModelProperty("Title of Advertise")
	private String title;
	@ApiModelProperty("Price of Advertise")
	private int price;
	@ApiModelProperty("Category Id of Advertise")
	private int categoryId;
	@ApiModelProperty("Category of Advertise")
	private String category;
	@ApiModelProperty("Discription of Advertise")
	private String description;
	@ApiModelProperty("Username, Who create the Advertise")
	private String username;
	@ApiModelProperty("Created Date of Advertise")
	private LocalDate createdDate;
	@ApiModelProperty("Modified Date of Advertise")
	private LocalDate modifiedDate;
	@ApiModelProperty("Current Status of Advertise")
	private String status;
	@ApiModelProperty("Advertise is Active/Inactive")
	private int active;
	@ApiModelProperty("PostedBy, Who Post the Advertise")
	private String postedBy;
	
	public Advertise() {}

	public Advertise(int categoryId, String title, int price, String description) {
		super();
		this.categoryId = categoryId;
		this.title = title;
		this.price = price;
		this.description = description;
	}

	
	public Advertise(String title, int price, int categoryId, String category, String description, String username,
			LocalDate createdDate, LocalDate modifiedDate, String status, int active, String postedBy) {
		super();
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
	
	public Advertise(int id, String title, int price, int categoryId, String category, String description, String username,
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
		return "Advertise [id=" + id + ", title=" + title + ", price=" + price + ", categoryId=" + categoryId
				+ ", category=" + category + ", description=" + description + ", username=" + username
				+ ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", status=" + status + ", active="
				+ active + ", postedBy=" + postedBy + "]";
	}

}
