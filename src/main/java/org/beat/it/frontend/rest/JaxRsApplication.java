package org.beat.it.frontend.rest;

import io.swagger.annotations.Api;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author Martin Petruna
 */
@ApplicationPath("/service")
@Api
public class JaxRsApplication extends Application {
}