package com.example.crudmysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    // get all users
    @GetMapping
    public List<Person> getAllPersons() {
        return this.personRepository.findAll();
    }

    // get user by id
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable (value = "id") long personId) {
        return this.personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id :" + personId));
    }

    // create user
    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return this.personRepository.save(person);
    }

    // update user
    @PutMapping("/{id}")
    public Person updatePerson(@RequestBody Person person, @PathVariable ("id") long personId) {
        Person existingPerson = this.personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id :" + personId));
        existingPerson.setFirstName(person.getFirstName());
        existingPerson.setLastName(person.getLastName());
        return this.personRepository.save(existingPerson);
    }

    // delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable ("id") long personId){
        Person existingUser = this.personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("User not found with id :" + personId));
        this.personRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }
}

