package com.example.crudmysql;


import jdk.nashorn.internal.ir.CallNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {
    @InjectMocks    //mereu pentru clasa pe care o testam
    private PersonService service;
    @Mock
    private PersonRepository repository;

    @Test
    public void givenEntity_shouldReturnSuccess(){
        Person person = new Person();
        person.setFirstName("sasa");
        Mockito.when(repository.save(person)).thenReturn(person); //in interior apeleaza repositoru.save(clasa trebuie Mockuita pt. ca savePerson apeleaza o fct dint-o alta clasa)

        Person person1 = service.savePerson(person); //apelam fct. de testare

        assertEquals(person1.getFirstName(),"sasa"); //verificare

    }

    @Test
    public void givenPersonId_shouldReturnSuccess(){
        Person person = new Person();
        person.setId(3);
        Mockito.when(repository.findById(3L)).thenReturn(Optional.of(person)); //in interior apeleaza repositoru.save(clasa trebuie Mockuita pt. ca savePerson apeleaza o fct dint-o alta clasa)

        Person personById = service.getPersonById(3);

        assertEquals(personById.getId(),3);

    }

    @Test(expected = PersonNotFoundException.class)
    public void givenPersonId_shouldReturnFail(){
        Mockito.when(repository.findById(3L)).thenReturn(Optional.empty()); //in interior apeleaza repositoru.save(clasa trebuie Mockuita pt. ca savePerson apeleaza o fct dint-o alta clasa)

        Person personById = service.getPersonById(3);
    }
}