package org.igazl.learning.dvd.film.service;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.panache.common.Page;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.igazl.learning.dvd.film.dao.FilmEntity;
import org.igazl.learning.dvd.film.rest.Film;
import org.igazl.learning.dvd.film.rest.FilmTransformer;

import java.util.List;

@ApplicationScoped
public class FilmService {

    private final FilmTransformer filmTransformer;

    public FilmService(FilmTransformer filmTransformer) {
        this.filmTransformer = filmTransformer;
    }

    /*
    The @WithSession (or @WithTransaction) is required when Active Record pattern is used for retrieving data from DB.
    Alternatively the `Panache.withSession()` also fine to use.
    The session is automatically created for active record when it is called from a REST mapped method. eg: org.igazl.learning.dvd.film.rest.FilmResource.getAll


    When DB is used with Repository pattern the required session is automatically created.
     */
    @WithSession
    public Uni<List<Film>> getAll(int page, int size) {
        return FilmEntity
                .findAll()
                .page(Page.of(page, size))
                .list()
                .map(this::transformFilms);
    }

    @WithSession
    public Uni<Film> get(Long filmId) {
        return findById(filmId)
                .map(this::transformFilm);
    }

    private static Uni<FilmEntity> findById(Long filmId) {
        return FilmEntity.findById(filmId);
    }

    private List<Film> transformFilms(List<PanacheEntityBase> films) {
        return films.stream().map(film -> transformFilm((FilmEntity) film)).toList();
    }

    private Film transformFilm(FilmEntity film) {
        return filmTransformer.transform(film);
    }
}
