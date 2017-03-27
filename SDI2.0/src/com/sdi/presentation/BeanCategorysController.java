package com.sdi.presentation;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;

import com.sdi.dto.Category;

@ManagedBean(name = "categoryTableView")
@SessionScoped
public class BeanCategorysController extends Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// public Category category;

	public List<Category> categories = null;
	public List<Category> filteredCategory;

	
	public Category selectedCategory;

	@ManagedProperty("#{controller}")
	private BeanUser user;

	@PostConstruct
	public void init() {
		getCategories();

	}

	@PostConstruct
	public List<Category> getCategories() {
		categories = user.getCategories();
		return categories;
	}

	public Category getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(Category selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public BeanUser getUser() {
		return user;
	}

	public void setUser(BeanUser user) {
		this.user = user;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public void setFilteredTasks(List<Category> filteredCategory) {
		this.filteredCategory = filteredCategory;
	}

	public List<Category> getFilteredCategory() {
		return filteredCategory;
	}

	public void setFilteredCategory(List<Category> filteredCategory) {
		this.filteredCategory = filteredCategory;
	}

}
