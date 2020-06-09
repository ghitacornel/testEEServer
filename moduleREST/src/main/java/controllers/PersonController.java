package controllers;

import mappers.PersonMapper;
import model.PersonJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @EJB
    PersonService personService;

    final private PersonMapper mapper = new PersonMapper();

    @GET
    @Produces("text/json")
    public List<PersonJson> findAll() {
        logger.info("find all called");
        return mapper.convertToJson(personService.findAll());
    }

    @GET
    @Path("/{id}")
    @Produces("text/json")
    public PersonJson findById(@PathParam("id") Integer id) {
        return mapper.convertToJson(personService.findById(id));
    }

}
