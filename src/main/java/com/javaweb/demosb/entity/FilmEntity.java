package com.javaweb.demosb.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "film", schema = "sakila")
public class FilmEntity {
    @Id
    @Column(name = "film_id", columnDefinition = "smallint unsigned")
    private Integer filmId;

    @Column(name = "title", length = 128)
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "release_year", columnDefinition = "year")
    private Integer releaseYear;

    @Column(name = "language_id", columnDefinition = "tinyint unsigned")
    private Integer languageId;

    @Column(name = "original_language_id", columnDefinition = "tinyint unsigned")
    private Integer originalLanguageId;

    @Column(name = "rental_duration", columnDefinition = "tinyint unsigned")
    private Integer rentalDuration;

    @Column(name = "rental_rate", columnDefinition = "decimal(4,2)")
    private Double rentalRate;

    @Column(name = "length", columnDefinition = "smallint unsigned")
    private Integer length;

    @Column(name = "replacement_cost", columnDefinition = "decimal(5,2)")
    private Double replacementCost;

    @Column(name = "rating", columnDefinition = "enum('G', 'PG', 'PG-13', 'R', 'NC-17')")
    private String rating;

    @Column(name = "special_features", columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeatures;

    @Column(name = "last_update", columnDefinition = "timestamp default current_timestamp")
    private Date lastUpdate;
}
