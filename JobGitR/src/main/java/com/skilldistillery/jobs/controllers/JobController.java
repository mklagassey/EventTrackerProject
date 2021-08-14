package com.skilldistillery.jobs.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.jobs.entities.Category;
import com.skilldistillery.jobs.entities.Company;
import com.skilldistillery.jobs.entities.Job;
import com.skilldistillery.jobs.entities.Location;
import com.skilldistillery.jobs.services.CategoryService;
import com.skilldistillery.jobs.services.CompanyService;
import com.skilldistillery.jobs.services.JobsService;
import com.skilldistillery.jobs.services.LocationService;

@CrossOrigin({"*", "http://localhost:4202"})
@RestController
@RequestMapping("api")
public class JobController {
	
	@Autowired
	private JobsService jobsServ;
	@Autowired
	private CompanyService compServ;
	@Autowired
	private LocationService locationServ;
	@Autowired
	private CategoryService catServ;
	
	@GetMapping("categories")
	public List<Category> listCategories() {
		return catServ.allCategories();
	}
	
	@GetMapping("locations")
	public List<Location> listLocations() {
		return locationServ.allLocations();
	}
	
	@GetMapping("companies")
	public List<Company> listCompanies() {
		return compServ.allCompanies();
	}
	
	@GetMapping("jobs")
	public List<Job> listJobs() {
		return jobsServ.allJobs();
	}

	@GetMapping("jobs/{id}")
	public Job getJob(@PathVariable Integer id) {
		return jobsServ.findById(id);
	}
	
	@PostMapping("jobs")
	public Job addJob(@RequestBody Job job, HttpServletRequest req, HttpServletResponse resp) {
		Job newJob = new Job();

		try {
			newJob = jobsServ.create(job);
			resp.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(newJob.getId());
			resp.setHeader("Location", url.toString());
		} catch (Exception e) {
			resp.setStatus(400);
			e.printStackTrace();
		}
		return newJob;
	}
	
	@PutMapping("jobs")
	public Job update(@RequestBody Job job, HttpServletRequest req, HttpServletResponse resp) {
		try {
			job = jobsServ.update(job);
			if (job == null) {
				resp.setStatus(404);
			}
		} catch (Exception e) {
			resp.setStatus(400);
			job = null;
			e.printStackTrace();
		}
		return job;
	}
	
	@DeleteMapping("jobs/{id}")
	public void delete(@PathVariable Integer id, HttpServletResponse resp) {
		Boolean isDeleted = jobsServ.delete(id);
		if (isDeleted) {
			resp.setStatus(204);
		} else {
			resp.setStatus(404);
		}
	}

}
