package org.pfsilga.blogengine;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.internal.ResponseSpecificationImpl;
import io.vertx.core.impl.HAManager;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import java.util.List;

import static io.restassured.RestAssured.*;
import static javax.ws.rs.core.Response.Status.*;
import static javax.ws.rs.core.HttpHeaders.*;
import static javax.ws.rs.core.MediaType.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class AuthorResourceTest {

    private static final String FIRSTNAME = "Noomde";
    private static final String LASTNAME = "Eddie";
    private static final String PICTURE = "https://www.superherodb.com/pictures2/portraits/10/050/22.jpg";
    private static final String USERNAME = "eddienoom";

    private static final int NB_AUTHORS = 14;


    @Test
    public void shouldGetRequestedAuthor(){
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
        when()
                .get("/api/authors/"+USERNAME).
        then()
                .statusCode(OK.getStatusCode())
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .body("firstname", Is.is(FIRSTNAME))
                .body("lastname", Is.is(LASTNAME))
                .body("picture", Is.is(PICTURE))
                .body("username", Is.is(USERNAME));
    }

    @Test
    @Order(1)
    public void shouldGetInitialAuthors(){
        List<Author> authors =
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
        when()
                .get("/api/authors").
        then()
                .statusCode(OK.getStatusCode())
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .extract().body().as(getHeroTypeRef());
        assertEquals(NB_AUTHORS, authors.size());
    }


    private TypeRef<List<Author>> getHeroTypeRef() {
        return new TypeRef<List<Author>>() {
            // Kept empty on purpose
        };
    }
}
