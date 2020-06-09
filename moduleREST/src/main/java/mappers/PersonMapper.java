package mappers;

import entities.Person;
import model.PersonJson;

public class PersonMapper implements JsonModelMapper<PersonJson, Person> {

    @Override
    public PersonJson convertToJson(Person model) {
        if (model == null) return null;
        PersonJson json = new PersonJson();
        json.setId(model.getId());
        json.setName(model.getName());
        return json;
    }

    @Override
    public Person convertToModel(PersonJson json) {
        if (json == null) return null;
        Person model = new Person();
        model.setId(json.getId());
        model.setName(json.getName());
        return model;
    }

}
