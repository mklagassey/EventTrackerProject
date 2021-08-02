package com.skilldistillery.jobs.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;
	private String name;
	private String skills;
	private String description;
	private Integer pay;
	private Boolean active;

	@CreationTimestamp
	private LocalDateTime posted;
	
	@UpdateTimestamp
	private LocalDateTime updated;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	

	@ManyToMany
	@JoinTable(name = "job_seeker_has_job", 
	joinColumns = @JoinColumn(name = "job_id"), 
	inverseJoinColumns = @JoinColumn(name = "job_seeker_id"))
	List<JobSeeker> jobSeekers;

	@JsonIgnoreProperties(value = "jobs")	
	@ManyToMany
	@JoinTable(name = "job_has_contact", 
	joinColumns = @JoinColumn(name = "job_id"), 
	inverseJoinColumns = @JoinColumn(name = "contact_id"))
	List<Contact> contacts;

	public Job() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPay() {
		return pay;
	}

	public void setPay(Integer pay) {
		this.pay = pay;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LocalDateTime getPosted() {
		return posted;
	}

	public void setPosted(LocalDateTime posted) {
		this.posted = posted;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<JobSeeker> getJobSeekers() {
		return jobSeekers;
	}

	public void setJobSeekers(List<JobSeeker> jobSeekers) {
		this.jobSeekers = jobSeekers;
	}
	
	public void addJobSeeker(JobSeeker jobSeeker) {
		if (jobSeekers == null) {jobSeekers = new ArrayList<>();}
		
		if (! jobSeekers.contains(jobSeeker)) {
			jobSeekers.add(jobSeeker);
			jobSeeker.addJob(this);
		}
	}

	public void deleteJobSeeker(JobSeeker jobSeeker) {
		if (jobSeekers != null && jobSeekers.contains(jobSeeker)) {
			jobSeekers.remove(jobSeeker);
			jobSeeker.deleteJob(this);
		}
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Job [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", name=");
		builder.append(name);
		builder.append(", skills=");
		builder.append(skills);
		builder.append(", pay=");
		builder.append(pay);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		if (id != other.id)
			return false;
		return true;
	}
//	
}
