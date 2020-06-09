package controllers;

import mappers.PersonMapper;
import model.PersonJson;
import service.PersonService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/person")
public class PersonController {

    @EJB
    PersonService personService;

    final private PersonMapper mapper = new PersonMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PersonJson> findAll() {
        return mapper.convertToJson(personService.findAll());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonJson getById(@PathParam("id") Integer id) {
        return mapper.convertToJson(personService.getById(id));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Integer post(PersonJson json) {
        return personService.save(mapper.convertToModel(json));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void put(PersonJson json) {
        personService.replace(mapper.convertToModel(json));
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(PersonJson json) {
        personService.update(mapper.convertToModel(json));
    }

    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") Integer id) {
        personService.deleteById(id);
    }

}
