package com.skilldistillery.jobs.services;

import java.util.List;

import com.skilldistillery.jobs.entities.JobSeeker;
import com.skilldistillery.jobs.entities.JobSeeker;

public interface JobSeekersService {
	
	List<JobSeeker> allJobSeekers();

	JobSeeker findById(Integer id);

	JobSeeker create(JobSeeker jobSeeker);

	JobSeeker update(JobSeeker jobSeeker);

	void delete(Integer id);

}
