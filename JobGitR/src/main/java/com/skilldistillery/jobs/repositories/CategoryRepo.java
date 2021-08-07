package com.skilldistillery.jobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobs.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
}
