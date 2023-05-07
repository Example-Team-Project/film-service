package org.igazl.learning.dvd.film.client.actor;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/v1/actors")
@RegisterRestClient(configKey = "actor-service-client")
public interface ActorServiceClient {

    @GET
    Uni<List<Actor>> getActors(@QueryParam("actorIds") List<Long> actorIds);

}
