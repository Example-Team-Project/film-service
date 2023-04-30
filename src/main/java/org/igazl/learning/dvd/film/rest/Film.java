package org.igazl.learning.dvd.film.rest;

import org.igazl.learning.dvd.film.common.model.MpaaRating;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Film(
        Long id,
        String title,
        String description,
        Integer releaseYear,
        Long languageId,
        Integer rentalDuration,
        BigDecimal rentalRate,
        Integer length,
        BigDecimal replacementCost,
        MpaaRating rating,
        LocalDateTime lastUpdate
) { };
