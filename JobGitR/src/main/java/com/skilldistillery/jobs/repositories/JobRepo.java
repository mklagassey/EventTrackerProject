package com.skilldistillery.jobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobs.entities.Job;

public interface JobRepo extends JpaRepository<Job, Integer> {
	
}
