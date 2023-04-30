package org.igazl.learning.dvd.film.rest;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.igazl.learning.dvd.film.service.FilmService;
import org.jboss.resteasy.reactive.RestPath;

import java.util.List;

@Path("/v1/films")
@ApplicationScoped
public class FilmResource {

    private final FilmService filmService;

    public FilmResource(FilmService filmService) {
        this.filmService = filmService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Film>> getAll(
            @QueryParam("page")
            @DefaultValue("0")
            @Min(0)
            int page,
            @QueryParam("size")
            @DefaultValue("10")
            @Min(1)
            @Max(20)
            int size
    ) {
        return filmService.getAll(page, size);
    }

    @GET
    @Path("/{filmId:\\d+}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Film> getById(@RestPath Long filmId) {
        return filmService.get(filmId);
    }
}
