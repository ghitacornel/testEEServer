package controllers;

import mappers.PersonMapper;
import model.PersonJson;
import service.PersonService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Stateless
@Path("/person")
public class PersonController {

    @EJB
    PersonService personService;

    final private PersonMapper mapper = new PersonMapper();

    @GET
    @Produces("text/json")
    public List<PersonJson> findAll() {
        return mapper.convertToJson(personService.findAll());
    }

    @GET
    @Path("/{id}")
    @Produces("text/json")
    public PersonJson findById(@PathParam("id") Integer id) {
        return mapper.convertToJson(personService.findById(id));
    }

}
