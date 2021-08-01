package com.skilldistillery.jobs.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class JobSeekerTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private JobSeeker jobSeeker;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAJobs");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		jobSeeker = em.find(JobSeeker.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		jobSeeker = null;
	}

	@Test
	@DisplayName("test scalar fields")
	void test() {
		assertNotNull(jobSeeker);
		assertEquals("mick", jobSeeker.getFirstName());
		assertEquals("lagassey", jobSeeker.getLastName());
		assertEquals("student", jobSeeker.getTitle());
		assertEquals("mick@gmail.com", jobSeeker.getEmail());
		assertEquals("all around nice guy", jobSeeker.getDescription());
		assertNull(jobSeeker.getResume());
	}
	
	@Test
	@DisplayName("test joined fields")
	void test2() {
		assertNotNull(jobSeeker);
		assertEquals("remote", jobSeeker.getLocation().getState());
		assertEquals(3, jobSeeker.getJobs().size());
	}

}
