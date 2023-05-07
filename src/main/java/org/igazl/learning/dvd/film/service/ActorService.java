package org.igazl.learning.dvd.film.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.igazl.learning.dvd.film.client.actor.Actor;
import org.igazl.learning.dvd.film.client.actor.ActorServiceClient;

import java.util.List;

@ApplicationScoped
public class ActorService {

    private final ActorServiceClient actorServiceClient;

    public ActorService(@RestClient ActorServiceClient actorServiceClient) {
        this.actorServiceClient = actorServiceClient;
    }

    public Uni<List<org.igazl.learning.dvd.film.rest.Actor>> getActors(List<Long> actorIds) {
        return actorServiceClient.getActors(actorIds)
                .map(actors -> actors.stream().map(this::transform).toList());
    }

    private org.igazl.learning.dvd.film.rest.Actor transform(Actor actor) {
        return new org.igazl.learning.dvd.film.rest.Actor(actor.actorId(), actor.firstname(), actor.lastname());
    }
}
