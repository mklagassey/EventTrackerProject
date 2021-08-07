package com.skilldistillery.jobs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobs.entities.Location;
import com.skilldistillery.jobs.entities.Location;
import com.skilldistillery.jobs.repositories.LocationRepo;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationRepo locationRepo;

	@Override
	public List<Location> allLocations() {
		return locationRepo.findAll();
	}
	
	@Override
	public Location findById(Integer id) {
		Optional<Location> opt = locationRepo.findById(id);
		Location location = null;
		if (opt.isPresent()) {
			location = opt.get();
		}
		return location;
		}
	
	@Override
	public Location create(Location location) {
		return locationRepo.save(location);
	}

	@Override
	public Location update(Location location) {
		return locationRepo.saveAndFlush(location);
	}

	@Override
	public Boolean delete(Integer id) {
		Boolean deleted = false;
		Optional<Location> opt = locationRepo.findById(id);
		if (opt.isPresent()) {
			Location location = opt.get();
			if (location.getId() == id) {
				locationRepo.deleteById(id);
				deleted = true;
			}
		}
		return deleted;
	}

}
