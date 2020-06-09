package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.PersonJson;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class TestPersonController extends AbstractTest {

    @Test
    public void testFindAll() {
        RestAssured.given()
                .get(URL + "/moduleREST/rest/person")
                .then()
                .statusCode(200)
                .assertThat().body(Matchers.equalTo("[]"));
    }

    @Test
    public void testCRUD() throws Exception {

        PersonJson personJson = new PersonJson();

        // CREATE
        {
            personJson.setId(null);
            personJson.setName("alex");

            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(new ObjectMapper().writeValueAsString(personJson))
                    .post("/moduleREST/rest/person")
                    .then()
                    .statusCode(200)
                    .extract()
                    .response();

            String responseContent = response.getBody().asString();
            Assert.assertNotNull(responseContent);
            personJson.setId(Integer.valueOf(responseContent));
        }

        // FIND BY ID
        {
            RestAssured.given()
                    .get("/moduleREST/rest/person/" + personJson.getId())
                    .then()
                    .statusCode(200)
                    .assertThat().body(Matchers.equalTo(new ObjectMapper().writeValueAsString(personJson)));
        }

        // REWRITE
        {
            personJson.setName("marin");
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(new ObjectMapper().writeValueAsString(personJson))
                    .put("/moduleREST/rest/person")
                    .then()
                    .statusCode(204)
                    .assertThat().body(Matchers.emptyString());
        }

        // FIND BY ID
        {
            RestAssured.given()
                    .get("/moduleREST/rest/person/" + personJson.getId())
                    .then()
                    .statusCode(200)
                    .assertThat().body(Matchers.equalTo(new ObjectMapper().writeValueAsString(personJson)));
        }

        // UPDATE
        {
            personJson.setName("vasile");
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(new ObjectMapper().writeValueAsString(personJson))
                    .patch("/moduleREST/rest/person")
                    .then()
                    .statusCode(204)
                    .assertThat().body(Matchers.emptyString());
        }

        // FIND BY ID
        {
            RestAssured.given()
                    .get("/moduleREST/rest/person/" + personJson.getId())
                    .then()
                    .statusCode(200)
                    .assertThat().body(Matchers.equalTo(new ObjectMapper().writeValueAsString(personJson)));
        }

        // DELETE
        {
            RestAssured.given()
                    .delete("/moduleREST/rest/person/" + personJson.getId())
                    .then()
                    .statusCode(204)
                    .assertThat().body(Matchers.emptyString());
        }

        // FIND BY ID
        {
            RestAssured.given()
                    .get("/moduleREST/rest/person/" + personJson.getId())
                    .then()
                    .statusCode(204)
                    .assertThat().body(Matchers.emptyString());
        }
    }
}
