package com.cooksys.Friendlr.person;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cooksys.CustomExceptions.NameNotAvailableException;
import com.cooksys.CustomExceptions.NotFoundException;

@RestController
@RequestMapping("person")
public class PersonController {
	private PersonService personService;
	public PersonController(PersonService personService){
		this.personService = personService;
	}
	
	@GetMapping
	public List<PersonDto> getAll() {
		return personService.getPeople();
	}
	
	@GetMapping("{id}")
	public PersonDto getById(@PathVariable Long id) throws NotFoundException  {
		return personService.getById(id);
	}
	
	@GetMapping("{id}/friends")
	public List<PersonDto> getFriends(@PathVariable Long id)  {
		return personService.getFriends(id);
	}
	
	@PostMapping
	public PersonDto postPerson(@RequestBody PersonDto person) throws NameNotAvailableException {
		personService.postPerson(person);
		return person;
	}
	
	@PutMapping("{id}")
	public PersonDto putPerson(@RequestBody PersonDto person, @PathVariable Long id) throws NotFoundException {
		personService.putPerson(person, id);
		return person;
	}
	
	@DeleteMapping("{id}")
	public Long deletePerson(@PathVariable Long id) throws NotFoundException {
		personService.delPerson(id);
		return id;
	}
}
