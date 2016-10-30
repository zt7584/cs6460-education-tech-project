package edu.dev.entity;

import org.springframework.data.annotation.Id;

public class MongoCustomer {

	@Id
	public String id;

	public String first;
	public String last;

	public MongoCustomer() {
	}

	public MongoCustomer(String first, String last) {
		this.first = first;
		this.last = last;
	}

	public MongoCustomer(String id, String firstName, String lastName) {
		this.id = id;
		this.first = firstName;
		this.last = lastName;
	}

	@Override
	public String toString() {
		return String.format("MongoCustomer[id=%s, firstName='%s', lastName='%s']", id, first, last);
	}

}
