package org.beat.it.frontend.rest.swagger;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import javax.ws.rs.core.MultivaluedMap;

/**
 * reused from examples https://github.com/wildfly-swarm/wildfly-swarm-examples/blob/master/jaxrs/swagger/src/main/java/org/wildfly/swarm/examples/jaxrs/swagger/CORSFilter.java
 *
 * @author Yoshimasa Tanabe
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        final MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        headers.add("Access-Control-Max-Age", "-1");
        headers.add("Access-Control-Allow-Headers", "Authorization, Origin, X-Requested-With, Content-Type, Accept");
        headers.add("Access-Control-Expose-Headers", "Authorization, Content-Type");
    }

}