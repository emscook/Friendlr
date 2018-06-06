package com.cooksys.Friendlr.person;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.cooksys.CustomExceptions.NameNotAvailableException;
import com.cooksys.CustomExceptions.NotFoundException;

@Service
public class PersonService {
	private PersonMapper personMapper;
	private static Long globeID = Long.valueOf(1);
	private ArrayList<Person> people;
	
	PersonService(PersonMapper personMapper) {
		people = new ArrayList<Person>();
		this.personMapper = personMapper;
		constructSimplePeople();
	}
	
	public void setPeople(ArrayList<Person> people) {
		this.people = people;
	}
	
	public List<PersonDto> getPeople() {
		return people.stream().map(personMapper::toDto).collect(Collectors.toList());
	}
	
	public PersonDto getById(Long id) throws NotFoundException {
		for(Person person : this.people) {
			if(person.getId() == id) {
				return personMapper.toDto(person);
			}
		}
		throw new NotFoundException();
	}
	
	public List<PersonDto> getFriends(Long id) {
		for(Person person : people) {
			if(person.getId() == id) {
				if (person.getFriends().size() > 0) {
					return person.getFriends().stream().map(personMapper::toDto).collect(Collectors.toList());
				}
			}
		}
		return null;
	}
	
	public PersonDto postPerson(PersonDto personDto) throws NameNotAvailableException {
		Person ourGuy = personMapper.fromDto(personDto);
		if (ourGuy.getId() == null)  {
			throw new NameNotAvailableException(ourGuy.getFirstName(),ourGuy.getLastName());
		}
		ourGuy.setId(globeID++);
		people.add(ourGuy);
		return personMapper.toDto(ourGuy);
	}
	
	public void putPerson(PersonDto person, Long id) throws NotFoundException {
		Person insertGuy = personMapper.fromDto(person);
		insertGuy.setId(id);
		if(people.indexOf(insertGuy) != -1) {
			for(Person someGuy : people) {
				int indy = someGuy.getFriends().indexOf(insertGuy);
				if (indy  != -1){
					someGuy.getFriends().set(indy, insertGuy);
				}
			}
			people.set(people.indexOf(insertGuy), insertGuy);
		}
		else {
			throw new NotFoundException();
		}
	}
	
	public void delPerson(Long id) throws NotFoundException {
		Person delGuy = new Person();
		delGuy.setId(id);
		if (people.contains(delGuy)) {
			System.out.println("haha");
			people.remove(delGuy);
		}
		else {
			System.out.println("nani?!");
			throw new NotFoundException();
		}
		for(Person ecks : people) 
			ecks.getFriends().remove(delGuy);
	}
	private void constructSimplePeople() {
		ArrayList<Person> patsFriends = new ArrayList<Person>();
		ArrayList<Person> tatesFriends = new ArrayList<Person>();
		ArrayList<Person> jonsFriends = new ArrayList<Person>();
		Person pat = new Person("Patrick", "Doane");
		Person tate = new Person("Tate", "Dude");
		Person jon = new Person("Jon", "Doane");
		Person kate = new Person("Kate", "Dude");
		Person tom = new Person("Tom", "XD");
		Person igor = new Person("Igor", "Friendless");

		pat.setId(globeID++);
		tate.setId(globeID++);
		jon.setId(globeID++);
		kate.setId(globeID++);
		tom.setId(globeID++);
		igor.setId(globeID++);

		people.add(pat);
		people.add(tate);
		people.add(jon);
		people.add(kate);
		people.add(tom);
		people.add(igor);

		patsFriends.add(tom);
		patsFriends.add(jon);
		pat.setFriends(patsFriends);
		tatesFriends.add(jon);
		tatesFriends.add(tom);
		tate.setFriends(tatesFriends);
		jonsFriends.add(kate);
		jon.setFriends(jonsFriends);
	}
}
