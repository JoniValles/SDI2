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

	public List<Category> categorys = null;
	public List<Category> filteredCategory;

	public Category selectedCategory;

	@ManagedProperty("#{controller}")
	private BeanUser user;

	@PostConstruct
	public void init() {
		getCategorys();

	}

	@PostConstruct
	public List<Category> getCategorys() {
		categorys = user.getCategorys();
		return categorys;
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

	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
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
