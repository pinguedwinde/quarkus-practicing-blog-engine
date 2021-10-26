package org.pfsilga.blogengine;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Liveness
@ApplicationScoped
public class AuthorResourceHealthCheck implements HealthCheck {
    @Inject
    AuthorResource authorResource;

    @Override
    public HealthCheckResponse call() {
        authorResource.getAllAuthors();
        return HealthCheckResponse.named("Ping Author API Endpoint").up().build();
    }
}
