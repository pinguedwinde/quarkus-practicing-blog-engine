package org.pfsilga.blogengine.posts;

import com.github.javafaker.Faker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;
import org.pfsilga.blogengine.posts.PostResource.Tags;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Path("/api/posts")
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

    private static final Logger LOGGER = Logger.getLogger("PostResource");

    @Inject
    @RestClient
    AuthorResourceProxy authorResourceProxy;

    @GET
    @Path("/random")
    @Fallback(fallbackMethod = "fallbackGetRandomPost")
    @Counted(
            name = "getRandomPost",
            description = "Counts how many times the getRandomPost method has been invoked"
    )
    public Response getRandomPost(){
        Author author = authorResourceProxy.getRandomAuthor().readEntity(Author.class);
        Faker faker = new Faker();
        JsonObject post = Json.createObjectBuilder()
                .add("author", Json.createObjectBuilder()
                        .add("firstname", author.getFirstname())
                        .add("lastname", author.getLastname())
                        .add("picture", author.getPicture())
                        .add("username", author.getUsername())
                )
                .add("title", faker.book().title())
                .add("genre", faker.book().genre())
                .add("tags", faker.options().option(Tags.class).toString())
                .add("date", LocalDate.now().toString())
                .build();
        LOGGER.info("Random post " + post);
        return Response.ok(post).build();
    }

    public Response fallbackGetRandomPost(){
        LOGGER.warn("Fall back creating a random post.");
        Faker faker = new Faker();
        JsonObject post = Json.createObjectBuilder()
                .add("author", Json.createObjectBuilder()
                        .add("firstname", faker.name().firstName())
                        .add("lastname", faker.name().lastName())
                        .add("picture", "https://www.superherodb.com/pictures2/portraits/11/050/14033.jpg")
                        .add("username", "dummy username as it's a fake author")
                )
                .add("title", faker.book().title())
                .add("genre", faker.book().genre())
                .add("tags", faker.options().option(Tags.class).toString())
                .add("date", LocalDate.now().toString())
                .build();
        LOGGER.info("Random post " + post);
        return Response.ok(post).build();
    }

    public enum Tags {
        SOCIAL, TECH, NEW, UNUSUAL, HEALTH, BUSINESS, ECONOMICS, POLITICS, EDUCATION, ENTERTAINEMENT, CARE;
    }
}