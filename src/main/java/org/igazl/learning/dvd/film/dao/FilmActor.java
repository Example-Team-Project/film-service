package org.igazl.learning.dvd.film.dao;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "film_actor")
public class FilmActor extends PanacheEntityBase {

    @EmbeddedId
    public FilmActorPK pk;
    public LocalDateTime lastUpdate;

    @RegisterForReflection
    @Embeddable
    public static class FilmActorPK implements Serializable {
        @Column(name = "actor_id")
        public Long actorId;
        @Column(name = "film_id")
        public Long filmId;
    }
}


