package org.igazl.learning.dvd.film.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * When the @ApplicationPath is defined the whole app will get the prefix in the endpoint path.
 * eg: a @Path("/endpoint") would be /dvd/endpoint when this is set
 */
@ApplicationPath("/api")
public class RootEndpointApplication extends Application {
}
