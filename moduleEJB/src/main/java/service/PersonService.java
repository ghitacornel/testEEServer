package service;

import entities.Person;
import exceptions.BusinessException;
import repositories.PersonRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PersonService {

    @EJB
    PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person getById(Integer id) {
        return personRepository.getById(id);
    }

    public Integer save(Person person) {
        if (person.getId() == null) {
            personRepository.persist(person);
        } else {
            replace(person);
        }
        return person.getId();
    }

    public void replace(Person person) {
        if (person.getId() == null) {
            throw new BusinessException("Person with null id provided");
        } else if (personRepository.findById(person.getId()) == null) {
            personRepository.persist(person);
        } else {
            personRepository.merge(person);
        }
    }

    public void update(Person person) {
        if (personRepository.findById(person.getId()) == null) {
            throw new BusinessException("Person with provided id " + person.getId() + " not found");
        }
        personRepository.merge(person);
    }

    public void deleteById(Integer id) {
        personRepository.removeById(id);
    }

}
