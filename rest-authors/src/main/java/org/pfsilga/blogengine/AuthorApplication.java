package org.pfsilga.blogengine;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
@OpenAPIDefinition(
        info = @Info(
                title = "Authors API",
                description = "This API allows to retrieve all the authors for the blog engine",
                version = "1.0",
                contact = @Contact(name = "@pfsilga", url = "https://github.com/pinguedwinde")),
        externalDocs = @ExternalDocumentation(
                url = "https://github.com/pinguedwinde",
                description = "All the code for Quarkus Practicing with Blog Engine"),
        tags = {
                @Tag(name = "api", description = "Public API for blog engine")
        }
)
public class AuthorApplication extends Application {
}
