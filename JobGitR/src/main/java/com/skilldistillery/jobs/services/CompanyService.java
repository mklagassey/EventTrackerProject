package com.skilldistillery.jobs.services;

import java.util.List;

import com.skilldistillery.jobs.entities.Company;

public interface CompanyService {
	
	List<Company> allCompanies();

	Company findById(Integer id);

	Company create(Company job);

	Company update(Company job);

	Boolean delete(Integer id);

}
