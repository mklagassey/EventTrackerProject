package com.skilldistillery.jobs.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String city;
	private String state;
	private String country;

	public Location() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Location [id=");
		builder.append(id);
		builder.append(", city=");
		builder.append(city);
		builder.append(", state=");
		builder.append(state);
		builder.append(", country=");
		builder.append(country);
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
		Location other = (Location) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
