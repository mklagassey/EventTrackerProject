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


class JobTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Job job;

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
		job = em.find(Job.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		job = null;
	}

	@Test
	@DisplayName("test scalar fields")
	void test() {
		assertNotNull(job);
		assertEquals("", job.getName());
		assertEquals("junior java developer", job.getTitle());
		assertEquals("java, sql", job.getSkills());
		assertEquals("an exciting opportunity to make money and not starve", job.getDescription());
		assertTrue(job.getActive());
		assertEquals(100000, job.getPay());
	}
	
	@Test
	@DisplayName("test date fields")
	void test2() {
		assertNotNull(job);
		assertEquals(2021, job.getPosted().getYear());
		assertNull(job.getUpdated());
	}

	@Test
	@DisplayName("test joined fields")
	void test3() {
		assertNotNull(job);
		assertEquals("Randomtech", job.getCompany().getName());
		assertEquals("washington", job.getLocation().getCity());
		assertEquals("programming", job.getCategory().getName());
		assertEquals(2, job.getJobSeekers().size());
	}

}
