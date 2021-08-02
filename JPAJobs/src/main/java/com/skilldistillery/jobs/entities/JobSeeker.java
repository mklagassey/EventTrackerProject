package com.skilldistillery.jobs.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "job_seeker")
public class JobSeeker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private String title;
	private String description;
	private String resume;
	private String email;

	@ManyToOne
	private Location location;
	
	@JsonIgnoreProperties(value = "jobSeekers")	
	@ManyToMany(mappedBy = "jobSeekers")
	private List<Job> jobs;

	public JobSeeker() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
	
	public void addJob(Job job) {
		if (jobs == null) {
			jobs = new ArrayList<>();
		}

		if (!jobs.contains(job)) {
			jobs.add(job);
			job.addJobSeeker(this);
		}
	}

	public void deleteJob(Job job) {
		if (jobs != null && jobs.contains(job)) {
			jobs.remove(job);
			job.deleteJobSeeker(this);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobSeeker [id=");
		builder.append(id);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", title=");
		builder.append(title);
		builder.append(", resume=");
		builder.append(resume);
		builder.append(", email=");
		builder.append(email);
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
		JobSeeker other = (JobSeeker) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
