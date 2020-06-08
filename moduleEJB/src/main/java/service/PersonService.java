package service;

import model.Person;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PersonService {

    public List<Person> findAll() {
        List<Person> result = new ArrayList<>();
        {
            Person person = new Person();
            person.setId(1);
            person.setName("ion");
            result.add(person);
        }
        {
            Person person = new Person();
            person.setId(2);
            person.setName("gheorghe");
            result.add(person);
        }
        return result;
    }

    public Person findById(Integer id) {
        Person person = new Person();
        person.setId(1);
        person.setName("ion");
        return person;
    }

}
