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


class CompanyTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Company company;

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
		company = em.find(Company.class, 5);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		company = null;
	}

	@Test
	@DisplayName("test scalar fields")
	void test() {
		assertNotNull(company);
		assertEquals("Headhunters & Associates", company.getName());
		assertEquals("Taking names and collecting noggins for the right price", company.getDescription());
		assertEquals("Small", company.getSize());
		assertEquals("18008008000", company.getPhone());
	}

	@Test
	@DisplayName("test joined fields")
	void test2() {
		assertNotNull(company);
		assertEquals("Randomtech", company.getRecruitingFor().getName());
	}
}
