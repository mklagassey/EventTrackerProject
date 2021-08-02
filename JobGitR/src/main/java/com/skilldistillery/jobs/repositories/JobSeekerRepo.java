package com.skilldistillery.jobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobs.entities.Job;
import com.skilldistillery.jobs.entities.JobSeeker;

public interface JobSeekerRepo extends JpaRepository<JobSeeker, Integer> {
	
}
