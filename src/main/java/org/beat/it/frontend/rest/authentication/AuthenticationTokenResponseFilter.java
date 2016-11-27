package org.beat.it.frontend.rest.authentication;

import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * @author Martin Petruna
 */
@Slf4j
@Provider
@AuthenticationToken
@ApplicationScoped
public class AuthenticationTokenResponseFilter implements ContainerResponseFilter {

    @Inject
    TokenHolder tokenHolder;

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        log.debug("filtering response...");
        if (tokenHolder.getToken() != null) {
            containerResponseContext.getHeaders().putSingle(HttpHeaders.AUTHORIZATION, "Bearer " + tokenHolder.getToken());
        }
    }
}
