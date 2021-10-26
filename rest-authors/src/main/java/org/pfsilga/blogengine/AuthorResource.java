package org.pfsilga.blogengine;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/api/authors")
@Produces(MediaType.APPLICATION_JSON)
public class AuthorResource {

    public static final Logger LOGGER = Logger.getLogger(AuthorResource.class);

    @GET
    @Operation(summary = "Retrieves all authors")
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = Author.class, type = SchemaType.ARRAY)
            )
    )
    @APIResponse(responseCode = "204", description = "No authors")
    public Response getAllAuthors(){
        LOGGER.info("Retrieved all authors");
        return Response.ok(Author.findAll().list()).build();
    }

    @GET
    @Path("/{username}")
    @Operation(summary = "Retrieves an author by username")
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = Author.class)
            )
    )
    @APIResponse(responseCode = "204", description = "Author not found")
    public Response getAuthorByUsername(@PathParam("username") String username){
        Author author = Author.findByUsername(username);
        LOGGER.info("Retrieved author : " + author);
        return Response.ok(author).build();
    }

    @GET
    @Path("/random")
    @Operation(summary = "Retrieves a random author")
    @APIResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = Author.class)
            )
    )
    @APIResponse(responseCode = "204", description = "Author not found")
    public Response getRandomAuthor(){
        Author author = Author.findRandomAuthor();
        LOGGER.info("Retrieved author : " + author);
        return Response.ok(author).build();
    }
}
