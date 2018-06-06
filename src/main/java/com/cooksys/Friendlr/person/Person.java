package com.cooksys.Friendlr.person;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private Long id;
	private String firstName;
	private String lastName;
	private List<Person> friends;
	Person(){
		this.friends = new ArrayList<Person>();
	}
	
	public Person(Long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.friends = new ArrayList<Person>();
	}
	public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.friends = new ArrayList<Person>();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public List<Person> getFriends() {
		return friends;
	}
	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
