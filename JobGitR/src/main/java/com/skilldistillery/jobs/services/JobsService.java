package com.skilldistillery.jobs.services;

import java.util.List;

import com.skilldistillery.jobs.entities.Job;

public interface JobsService {
	
	List<Job> allJobs();

	Job findById(Integer id);

	Job create(Job job);

	Job update(Job job);

	void delete(Integer id);

}
