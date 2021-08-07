package com.skilldistillery.jobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobs.entities.Company;

public interface CompanyRepo extends JpaRepository<Company, Integer> {
	
}
