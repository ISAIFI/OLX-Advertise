package com.olx.service;

import java.util.List;
import java.util.Map;

import com.olx.dto.Category;
import com.olx.dto.Status;

public interface MasterDataDelegate {

	public List<Map> getAllCategories();
	public Category getCategoryDetails(int categoryId);
	public Status getStatusDetails(int statusId);
}
