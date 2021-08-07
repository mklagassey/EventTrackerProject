package com.skilldistillery.jobs.services;

import java.util.List;

import com.skilldistillery.jobs.entities.Category;

public interface CategoryService {
	
	List<Category> allCategories();

	Category findById(Integer id);

	Category create(Category category);

	Category update(Category category);

	Boolean delete(Integer id);

}
