package com.skilldistillery.jobs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobs.entities.JobSeeker;
import com.skilldistillery.jobs.repositories.JobRepo;
import com.skilldistillery.jobs.repositories.JobSeekerRepo;

@Service
public class JobSeekersServiceImpl implements JobSeekersService {
	
	@Autowired
	private JobSeekerRepo jobSeekerRepo;

	@Override
	public List<JobSeeker> allJobSeekers() {
		return jobSeekerRepo.findAll();
	}
	
	@Override
	public JobSeeker findById(Integer id) {
		Optional<JobSeeker> opt = jobSeekerRepo.findById(id);
		JobSeeker job = null;
		if (opt.isPresent()) {
			job = opt.get();
		}
		return job;
		}
	
	@Override
	public JobSeeker create(JobSeeker jobSeeker) {
		return jobSeekerRepo.save(jobSeeker);
	}

	@Override
	public JobSeeker update(JobSeeker jobSeeker) {
		return jobSeekerRepo.saveAndFlush(jobSeeker);
	}

	@Override
	public void delete(Integer id) {
		 jobSeekerRepo.deleteById(id);
	}

}
