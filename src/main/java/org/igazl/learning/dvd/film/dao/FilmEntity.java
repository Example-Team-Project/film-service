package org.igazl.learning.dvd.film.dao;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.igazl.learning.dvd.film.common.model.MpaaRating;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "film")
public class FilmEntity extends PanacheEntityBase {

    @Id
    @Column(name = "film_id")
    public Long id;
    public String title;
    public String description;
    public Integer releaseYear;
    public Long languageId;
    public Integer length;
    public Integer rentalDuration;
    public BigDecimal rentalRate;
    public BigDecimal replacementCost;
    @Convert(converter = RatingConverter.class)
    public MpaaRating rating;
    public LocalDateTime lastUpdate;


}
