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


class ContactTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Contact contact;

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
		contact = em.find(Contact.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		contact = null;
	}

	@Test
	@DisplayName("test scalar fields")
	void test() {
		assertNotNull(contact);
		assertEquals("sara", contact.getFirstName());
		assertEquals("smith", contact.getLastName());
		assertEquals("sara@headhunters.biz", contact.getEmail());
		assertEquals("18882223333", contact.getPhone());
		assertEquals("recruiter", contact.getTitle());
		assertNull(contact.getPosition());
		assertNull(contact.getNotes());
		
	}
	
	@Test
	@DisplayName("test joined fields")
	void test2() {
		assertNotNull(contact);
		assertEquals("Headhunters & Associates", contact.getCompany().getName());
		assertEquals(1, contact.getJobs().size());
	}

}
