package controllers;

import mappers.PersonMapper;
import model.PersonJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.PersonService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("/person")
public class PersonController {

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @EJB
    PersonService personService;

    final private PersonMapper mapper = new PersonMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PersonJson> findAll() {
        logger.info("find all called");
        return mapper.convertToJson(personService.findAll());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PersonJson findById(@PathParam("id") Integer id) {
        return mapper.convertToJson(personService.findById(id));
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
