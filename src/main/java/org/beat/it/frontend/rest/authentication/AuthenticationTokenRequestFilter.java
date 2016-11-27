package org.beat.it.frontend.rest.authentication;

import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * @author Martin Petruna
 */
@Slf4j
@Provider
@AuthenticationToken
@ApplicationScoped
public class AuthenticationTokenRequestFilter implements ContainerRequestFilter {

    @Inject
    TokenHolder tokenHolder;
    @Inject
    TokenGenerator tokenGenerator;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        log.debug("filtering request...");
        // Get the HTTP Authorization header from the request
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        String token = null;
        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            //generate fresh token
            token = tokenGenerator.generateToken();
        } else {
            // Extract the token from the HTTP Authorization header
            token = authorizationHeader.substring("Bearer".length()).trim();
        }
        tokenHolder.setToken(token);

        try {
            // Validate the token
            validateToken(token);
        } catch (Exception e) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private void validateToken(String token) throws Exception {
        //some validation
    }
}
