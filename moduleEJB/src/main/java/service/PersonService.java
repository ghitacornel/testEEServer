package service;

import dao.exceptions.DAOEntityNotFoundException;
import entities.Person;
import repositories.PersonRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Stateless
public class PersonService {

    @EJB
    PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Integer id) {

        return personRepository.findById(id);
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
            throw new DAOEntityNotFoundException("Person");
        } else if (personRepository.findById(person.getId()) == null) {
            personRepository.persist(person);
        } else {
            personRepository.merge(person);
        }
    }

    public void update(Person person) {
        if (personRepository.findById(person.getId()) == null) {
            throw new DAOEntityNotFoundException("Person", person.getId());
        }
        personRepository.merge(person);
    }

    public void deleteById(Integer id) {
        personRepository.removeById(id);
    }

}
