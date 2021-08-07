package com.skilldistillery.jobs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobs.entities.Category;
import com.skilldistillery.jobs.entities.Category;
import com.skilldistillery.jobs.repositories.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo catRepo;

	@Override
	public List<Category> allCategories() {
		return catRepo.findAll();
	}
	
	@Override
	public Category findById(Integer id) {
		Optional<Category> opt = catRepo.findById(id);
		Category category = null;
		if (opt.isPresent()) {
			category = opt.get();
		}
		return category;
		}
	
	@Override
	public Category create(Category category) {
		return catRepo.save(category);
	}

	@Override
	public Category update(Category category) {
		return catRepo.saveAndFlush(category);
	}

	@Override
	public Boolean delete(Integer id) {
		Boolean deleted = false;
		Optional<Category> opt = catRepo.findById(id);
		if (opt.isPresent()) {
			Category category = opt.get();
			if (category.getId() == id) {
				catRepo.deleteById(id);
				deleted = true;
			}
		}
		return deleted;
	}

}
