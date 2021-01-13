package com.example.crudmysql;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/api/users")
public class PersonController {

    private final PersonService service;

//    @Autowired
//    public void setPersonRepository(PersonRepository personRepository) {
//        this.personRepository = personRepository;
//    }

    //@Autowired
    //private PersonRepository personRepository;

    public PersonController(PersonService service) {
        this.service = service;
    }

    // get all users
    @GetMapping
    public List<Person> getAllPersons() {
        //return this.personRepository.findAll();
        return service.getAllPersons();
    }

    // get user by id
    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable (value = "id") long personId) {
        /*return this.personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id :" + personId));
                */
        return service.getPersonById(personId);

    }

    // create user
    @PostMapping()
    public Person createPerson(@RequestBody Person person) {
        //return this.personRepository.save(person);
        return service.savePerson(person);
    }

    // update user
    @PutMapping("/{id}")
    public Person updatePerson(@RequestBody Person person) {
        /*Person existingPerson = this.personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id :" + personId));
        existingPerson.setFirstName(person.getFirstName());
        existingPerson.setLastName(person.getLastName());
        return this.personRepository.save(existingPerson);*/
        //Person existingPerson = this.service.getPersonById(personId)
        return service.updatePerson(person);
    }

    // delete user by id
    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable ("id") long personId){
       return service.deletePerson(personId);
    }
}

