package org.pfsilga.blogengine.posts;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@RegisterRestClient
@Path("/api/authors")
@Produces(MediaType.APPLICATION_JSON)
public interface AuthorResourceProxy{

    @GET
    @Path("/random")
    Response getRandomAuthor();
}
