package org.igazl.learning.dvd.film.rest;

import jakarta.enterprise.context.ApplicationScoped;
import org.igazl.learning.dvd.film.dao.FilmEntity;

@ApplicationScoped
public class FilmTransformer {

    public Film transform(FilmEntity film) {

        return new Film(
                film.id,
                film.title,
                film.description,
                film.releaseYear,
                film.languageId,
                film.rentalDuration,
                film.rentalRate,
                film.length,
                film.replacementCost,
                film.rating,
                film.lastUpdate
        );
    }

}
