package repositories;

import entities.Person;

import javax.ejb.Stateless;

@Stateless
public class PersonRepository extends PostgresDAO<Person, Integer> {

    public PersonRepository() {
        super(Person.class);
    }

}
