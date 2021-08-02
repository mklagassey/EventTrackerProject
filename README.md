# EventTrackerProject

### Full-Stack Spring/REST/JPA Project for Skill Distillery

## Overview
* A basic RESTful api for keeping track of job postings and interested applicants. Includes access to full CRUD operations for both entities as shown below. Optional entities are: location (both applicants and jobs) and category (jobs). Required fields are: "company" for jobs and contacts, "title" for jobs, "firstName" and "lastName" for job seekers and "name" for company, all others are optional.

## REST Endpoints

| Method | URI                | Request Body | Response Body |
|--------|--------------------|--------------|---------------|
| GET    | `/api/jobs`        |              | Collection of representations of all job resources
| GET    | `/api/jobs/{id}`   |              | Single job resource |
| POST   | `/api/jobs`        | {"title": STRING, "name": STRING, "skills": STRING, "description": STRING, "pay": INTEGER, "company": {"id": INTEGER}, "location": {"id": INTEGER}, "category": {"id": INTEGER}, "jobSeekers": {"id": INTEGER},"contacts": {"id": INTEGER}} | {"title": STRING, "name": STRING, "skills": STRING, "description": STRING, "pay": INTEGER,"active": true,"posted": DATE-TIME,"updated": DATE-TIME,"company": info,"location": info,"category": info,"jobSeekers": info,"contacts": info} |
| PUT | `/api/jobs` | {"title": STRING, "name": STRING, "skills": STRING, "description": STRING, "pay": INTEGER, "company": {"id": INTEGER}, "location": {"id": INTEGER}, "category": {"id": INTEGER}, "jobSeekers": {"id": INTEGER},"contacts": {"id": INTEGER}} | {"title": STRING, "name": STRING, "skills": STRING, "description": STRING, "pay": INTEGER,"active": true,"posted": DATE-TIME,"updated": DATE-TIME,"company": info,"location": info,"category": info,"jobSeekers": info,"contacts": info} |
| DELETE | `/api/jobs/{id}` |             | 204 if deleted, 404 if not found |
| GET | `/api/jobseekers` | | Collection of representations of all job applicants |
| GET | `/api/jobseekers/{id}` | | Single job applicant |
| POST | `/api/jobseekers` | {"firstName": "STRING","lastName": "STRING","title": "STRING","description": "STRING","resume": STRING,"email": "STRING"}, "location":{"id": INTEGER}, "jobs": [{"id": INTEGER}] | {"firstName": "STRING","lastName": "STRING","title": "STRING","description": "STRING","resume": STRING,"email": "STRING","location": info,"jobs": info} |
| PUT | `/api/jobseekers` | {"firstName": "STRING","lastName": "STRING","title": "STRING","description": "STRING","resume": STRING,"email": "STRING"}, "location":{"id": INTEGER}, "jobs": [{"id": INTEGER}] | {"firstName": "STRING","lastName": "STRING","title": "STRING","description": "STRING","resume": STRING,"email": "STRING","location": info,"jobs": info} |
| DELETE | `/api/jobseekers/{id}` | | |
