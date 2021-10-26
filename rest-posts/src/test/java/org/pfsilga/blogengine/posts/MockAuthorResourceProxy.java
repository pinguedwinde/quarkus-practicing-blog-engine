package org.pfsilga.blogengine.posts;

import com.github.javafaker.Faker;
import io.quarkus.test.Mock;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

@Mock
@RestClient
@ApplicationScoped
public class MockAuthorResourceProxy implements AuthorResourceProxy{
    @Override
    public Response getRandomAuthor() {
        Faker faker = new Faker();
        JsonObject post = Json.createObjectBuilder()
                .add("author", Json.createObjectBuilder()
                        .add("firstname", faker.name().firstName())
                        .add("lastname", faker.name().lastName())
                        .add("picture", "https://www.superherodb.com/pictures2/portraits/11/050/14033.jpg")
                        .add("username", "dummy username as it's a fake author")
                )
                .build();
        return Response.ok(post).build();
    }
}
