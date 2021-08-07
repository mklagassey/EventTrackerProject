package com.skilldistillery.jobs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.jobs.entities.Location;

public interface LocationRepo extends JpaRepository<Location, Integer> {
	
}
