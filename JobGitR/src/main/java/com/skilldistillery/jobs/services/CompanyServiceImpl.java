package com.skilldistillery.jobs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobs.entities.Company;
import com.skilldistillery.jobs.repositories.CompanyRepo;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyRepo companyRepo;

	@Override
	public List<Company> allCompanies() {
		return companyRepo.findAll();
	}
	
	@Override
	public Company findById(Integer id) {
		Optional<Company> opt = companyRepo.findById(id);
		Company company = null;
		if (opt.isPresent()) {
			company = opt.get();
		}
		return company;
		}
	
	@Override
	public Company create(Company company) {
		return companyRepo.save(company);
	}

	@Override
	public Company update(Company job) {
		return companyRepo.saveAndFlush(job);
	}

	@Override
	public Boolean delete(Integer id) {
		Boolean deleted = false;
		Optional<Company> opt = companyRepo.findById(id);
		if (opt.isPresent()) {
			Company job = opt.get();
			if (job.getId() == id) {
				companyRepo.deleteById(id);
				deleted = true;
			}
		}
		return deleted;
	}

}
