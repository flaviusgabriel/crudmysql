package com.example.crudmysql;

import javafx.beans.value.ObservableBooleanValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class PersonService {
    /*private List<Person> persons = new ArrayList<>(Arrays.asList(new Person(1,"fName1","lName1"),
                                                                 new Person(2,"fName2","lName2"),
                                                                 new Person(3,"fName3","lName3")));

    public ObservableBooleanValue getPersons() {
        System.out.println("PersonService.getPersons() invoked " + persons );
        return (ObservableBooleanValue) persons;
    }

    public Person getPersons (int id) {
        System.out.println("PersonService.getPersons(id) invoked " + id );
        return persons.stream().filter(t -> t.getId() == id).findFirst().get();
    }

    public void addPersons (Person person) {
        persons.add(person);
        System.out.println("PersonService.addPersons() invoked " + person);
    }

    public void updatePersons(Person person) {
        System.out.println("PersonService.updatePersons() invoked " + person);
    }

    public void deletePersons(int id) {
        System.out.println("PersonService.deletePerson() invoked " + id);
    }*/
    @Autowired
    private PersonRepository repository;

    public Person savePerson(Person person) {
        return repository.save(person);
    }

    public List<Person> savePersons(List<Person> persons) {
        return repository.saveAll(persons);
    }

    /*public List<Person> getPersons() {
        return repository.findAll();
    }*/

    public Person getPersonById(long id) {
        //return repository.findById((long) id).orElseThrow();
        return this.repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Person not found with id :" + id));
    }

    public List<Person> getAllPersons(){
        return repository.findAll();
    }

    /*public Person getPersonByFirstName(String firstName) {
        return repository.findAll(firstName);
    }

    public Person getPersonByLastName(String firstName) {
        return repository.findAll(lastName);
    }
    public Person getAllPersons(String firstName) {
        return repository.findAll(lastName);
    }*/


    public String deletePerson(long id) {
//        repository.deleteById(id);
//        return "person removed !! " + id;
        Person existingPerson = getPersonById(id);
        this.repository.delete(existingPerson);
        return "Person removed !! " + existingPerson;
    }

    public Person updatePerson(Person person) {
        Person existingPerson = getPersonById(person.getId());
        existingPerson.setFirstName(person.getFirstName());
        existingPerson.setLastName(person.getLastName());
        return repository.save(existingPerson);
    }

}
