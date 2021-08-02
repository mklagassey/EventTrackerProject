package com.skilldistillery.jobs.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.jobs.entities.JobSeeker;
import com.skilldistillery.jobs.services.JobSeekersService;
import com.skilldistillery.jobs.services.JobsService;


@RestController
@RequestMapping("api")
public class JobSeekerController {
	
	@Autowired
	private JobSeekersService jobSeekersServ;
	
	@GetMapping("jobseekers")
	public List<JobSeeker> listJobSeekers() {
		return jobSeekersServ.allJobSeekers();
	}

	@GetMapping("jobseekers/{id}")
	public JobSeeker getJobSeeker(@PathVariable Integer id) {
		return jobSeekersServ.findById(id);
	}
	
	@PostMapping("jobseekers")
	public JobSeeker addJobSeeker(@RequestBody JobSeeker jobSeeker, HttpServletRequest req, HttpServletResponse resp) {
		JobSeeker newJobSeeker = new JobSeeker();

		try {
			newJobSeeker = jobSeekersServ.create(jobSeeker);
			resp.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(newJobSeeker.getId());
			resp.setHeader("Location", url.toString());
		} catch (Exception e) {
			resp.setStatus(400);
			e.printStackTrace();
		}
		return newJobSeeker;
	}
	
	@PutMapping("jobseekers")
	public JobSeeker update(@RequestBody JobSeeker jobSeeker, HttpServletRequest req, HttpServletResponse resp) {
		try {
			jobSeeker = jobSeekersServ.update(jobSeeker);
			if (jobSeeker == null) {
				resp.setStatus(404);
			}
		} catch (Exception e) {
			resp.setStatus(400);
			jobSeeker = null;
			e.printStackTrace();
		}
		return jobSeeker;
	}
	
	@DeleteMapping("jobseekers/{id}")
	public void delete(@PathVariable Integer id, HttpServletResponse resp) {
		jobSeekersServ.delete(id);
	}

}
