package org.igazl.learning.dvd.film.service;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.panache.common.Page;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.igazl.learning.dvd.film.dao.FilmEntity;
import org.igazl.learning.dvd.film.rest.Actor;
import org.igazl.learning.dvd.film.rest.Film;
import org.igazl.learning.dvd.film.rest.FilmTransformer;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class FilmService {

    private final FilmTransformer filmTransformer;
    private final ActorService actorService;

    public FilmService(FilmTransformer filmTransformer, ActorService actorService) {
        this.filmTransformer = filmTransformer;
        this.actorService = actorService;
    }

    /*
    The @WithSession (or @WithTransaction) is required when Active Record pattern is used for retrieving data from DB.
    Alternatively the `Panache.withSession()` also fine to use.
    The session is automatically created for active record when it is called from a REST mapped method.
     eg: org.igazl.learning.dvd.film.rest.FilmResource.getAll

    When DB is used with Repository pattern the required session is automatically created.
     */
    @WithSession
    public Uni<List<Film>> getAll(int page, int size) {
        return FilmEntity
                .findAllAndFetch()
                .page(Page.of(page, size))
                .list().flatMap(this::transformFilms);
    }

    @WithSession
    public Uni<Film> get(Long filmId) {
        return findById(filmId).flatMap(this::transformFilm);
    }

    private static Uni<FilmEntity> findById(Long filmId) {
        return FilmEntity.findAndFetch(filmId);
    }

    private Uni<Film> transformFilm(FilmEntity film) {
        return getActorsUni(film).map(actors -> filmTransformer.transform(film, actors));
    }

    private Uni<List<Actor>> getActorsUni(FilmEntity film) {
        return actorService
                .getActors(getConnectedActorIds(film))
                .log()
                .onFailure().recoverWithItem(Collections::emptyList)
                .log();
    }

    private static List<Long> getConnectedActorIds(FilmEntity film) {
        return film.actors.stream().map(filmActor -> filmActor.pk.actorId).toList();
    }

    private Uni<List<Film>> transformFilms(List<FilmEntity> films) {
        List<Uni<Film>> unis = films.stream().map(this::transformFilm).toList();
        return Uni.combine().all().unis(unis).combinedWith(objects -> objects.stream().map(o -> (Film) o).toList());
    }

}
