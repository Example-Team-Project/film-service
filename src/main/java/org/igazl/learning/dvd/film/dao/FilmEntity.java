package org.igazl.learning.dvd.film.dao;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.hibernate.reactive.panache.PanacheQuery;
import io.smallrye.mutiny.Uni;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.igazl.learning.dvd.film.common.model.MpaaRating;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "film_id")
    public List<FilmActor> actors = List.of();

    public static PanacheQuery<PanacheEntityBase> findAllAndFetch() {
        return find( "select f from FilmEntity f left join fetch f.actors a");
    }

    public static Uni<FilmEntity> findAndFetch(long filmId) {
        return find( "select f from FilmEntity f left join fetch f.actors a where f.id = ?1", filmId)
                .firstResult();
    }

}
