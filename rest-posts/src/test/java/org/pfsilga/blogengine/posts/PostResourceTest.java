package org.pfsilga.blogengine.posts;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

@QuarkusTest
public class PostResourceTest {

    @Test
    public void shouldGetRandomPost() {
        given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).
        when()
                .get("/api/posts/random").
        then()
                .statusCode(Status.OK.getStatusCode())
                .body("$", hasKey("author"))
                .body("$", hasKey("title"))
                .body("$", hasKey("genre"))
                .body("$", hasKey("tags"))
                .body("$", hasKey("date"));
    }

}