package org.igazl.learning.dvd.film.rest;

import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.Tracer;
import jakarta.enterprise.context.ApplicationScoped;
import org.igazl.learning.dvd.film.dao.FilmEntity;

import java.util.List;

@ApplicationScoped
public class FilmTransformer {

    private final Tracer tracer;

    public FilmTransformer(Tracer tracer) {
        this.tracer = tracer;
    }

    public Film transform(FilmEntity film, List<Actor> actors) {
        var span = tracer.spanBuilder("transform-film")
                .setAttribute("film.id", film.id)
                .setSpanKind(SpanKind.INTERNAL)
                .startSpan();
        Film newFilm = new Film(
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
                film.lastUpdate,
                actors
        );
        span.end();
        return newFilm;
    }

}
