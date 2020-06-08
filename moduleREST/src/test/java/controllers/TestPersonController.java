package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import model.PersonJson;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestPersonController extends AbstractTest {

    @Test
    public void testFindAll() throws Exception {

        List<PersonJson> expected = new ArrayList<>();
        {
            PersonJson personJson = new PersonJson();
            personJson.setId(1);
            personJson.setName("ion");
            expected.add(personJson);
        }
        {
            PersonJson personJson = new PersonJson();
            personJson.setId(2);
            personJson.setName("gheorghe");
            expected.add(personJson);
        }

        RestAssured.get(URL + "/moduleREST/rest/person").then().statusCode(200).assertThat()
                .body(Matchers.equalTo(new ObjectMapper().writeValueAsString(expected)));
    }

    @Test
    public void testFindById() throws Exception {

        PersonJson expected = new PersonJson();
        expected.setId(1);
        expected.setName("ion");

        RestAssured.get(URL + "/moduleREST/rest/person/1").then().statusCode(200).assertThat()
                .body(Matchers.equalTo(new ObjectMapper().writeValueAsString(expected)));
    }
}
