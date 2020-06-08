package mappers;

import model.Person;
import model.PersonJson;

public class PersonMapper implements JsonModelMapper<PersonJson, Person> {

    @Override
    public PersonJson convertToJson(Person model) {
        PersonJson json = new PersonJson();
        json.setId(model.getId());
        json.setName(model.getName());
        return json;
    }

    @Override
    public Person convertToModel(PersonJson json) {
        Person model = new Person();
        model.setId(json.getId());
        model.setName(json.getName());
        return model;
    }

}
