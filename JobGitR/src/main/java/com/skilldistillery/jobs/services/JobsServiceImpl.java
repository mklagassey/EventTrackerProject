package com.skilldistillery.jobs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobs.entities.Job;
import com.skilldistillery.jobs.repositories.JobRepo;

@Service
public class JobsServiceImpl implements JobsService {
	
	@Autowired
	private JobRepo jobRepo;

	@Override
	public List<Job> allJobs() {
		return jobRepo.findAll();
	}
	
	@Override
	public Job findById(Integer id) {
		Optional<Job> opt = jobRepo.findById(id);
		Job job = null;
		if (opt.isPresent()) {
			job = opt.get();
		}
		return job;
		}
	
	@Override
	public Job create(Job job) {
		job.setActive(true);
		return jobRepo.save(job);
	}

	@Override
	public Job update(Job job) {
		return jobRepo.saveAndFlush(job);
	}

	@Override
	public Boolean delete(Integer id) {
		Boolean deleted = false;
		Optional<Job> opt = jobRepo.findById(id);
		if (opt.isPresent()) {
			Job job = opt.get();
			if (job.getId() == id) {
				jobRepo.deleteById(id);
				deleted = true;
			}
		}
		return deleted;
	}

}
