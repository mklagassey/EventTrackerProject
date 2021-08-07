package com.skilldistillery.jobs.services;

import java.util.List;

import com.skilldistillery.jobs.entities.Location;

public interface LocationService {
	
	List<Location> allLocations();

	Location findById(Integer id);

	Location create(Location location);

	Location update(Location location);

	Boolean delete(Integer id);

}
