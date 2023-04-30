package org.igazl.learning.dvd.film.dao;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnTransformer;
import org.igazl.learning.dvd.film.common.model.MpaaRating;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "film")
public class FilmEntity extends PanacheEntityBase {

    @Id
    @Column(name = "film_id")
    public Long id;
    @Column
    public String title;
    @Column
    public String description;
    @Column(name = "release_year")
    public Integer releaseYear;
    @Column(name = "language_id")
    public Long languageId;
    @Column
    public Integer length;
    @Column(name = "rental_duration")
    public Integer rentalDuration;
    @Column(name = "rental_rate")
    public BigDecimal rentalRate;
    @Column(name = "replacement_cost")
    public BigDecimal replacementCost;
    @Column
    @Convert(converter = RatingConverter.class)
    public MpaaRating rating;
    @Column(name = "last_update")
    public LocalDateTime lastUpdate;


}
