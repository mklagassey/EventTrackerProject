package com.skilldistillery.jobs.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String description;
	private String size;
	private String phone;
	
	@OneToOne
	@JoinColumn(name = "recruiting_for")
	private Company recruitingFor;
	
	public Company() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Company getRecruitingFor() {
		return recruitingFor;
	}

	public void setRecruitingFor(Company recruitingFor) {
		this.recruitingFor = recruitingFor;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Company [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", size=");
		builder.append(size);
		builder.append(", phone=");
		builder.append(phone);
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
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

	
}
