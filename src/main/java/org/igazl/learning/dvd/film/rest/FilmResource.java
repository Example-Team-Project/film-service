package org.igazl.learning.dvd.film.rest;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.igazl.learning.dvd.film.dao.FilmEntity;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

@Path("/v1/films")
@ApplicationScoped
public class FilmResource {

    private final FilmTransformer filmTransformer;

    public FilmResource(FilmTransformer filmTransformer) {
        this.filmTransformer = filmTransformer;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Film>> getAll() {
        return FilmEntity
                .listAll()
                .onItem().transform(t -> t.stream().map(x -> filmTransformer.transform((FilmEntity) x)).toList());
    }

    @GET
    @Path("/{filmId:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Film> getById(@RestPath Long filmId) {
        return FilmEntity
                .findById(filmId)
                .onItem()
                .transform(t -> filmTransformer.transform((FilmEntity) t));
    }
}
