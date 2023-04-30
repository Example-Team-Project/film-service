package org.igazl.learning.dvd.film.dao;

import jakarta.persistence.AttributeConverter;
import org.igazl.learning.dvd.film.common.model.MpaaRating;

import java.util.Optional;

public class RatingConverter implements AttributeConverter<MpaaRating, String> {
    @Override
    public String convertToDatabaseColumn(MpaaRating attribute) {
        return Optional.ofNullable(attribute)
                .map(Enum::name)
                .map(name -> name.replace("_", "-"))
                .orElse(null);
    }

    @Override
    public MpaaRating convertToEntityAttribute(String dbData) {
        return Optional.ofNullable(dbData)
                .map(value -> value.replace("-", "_"))
                .map(MpaaRating::valueOf)
                .orElse(null);
    }
}
