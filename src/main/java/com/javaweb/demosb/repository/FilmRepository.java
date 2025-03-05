package com.javaweb.demosb.repository;

import com.javaweb.demosb.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Short> {
    @Query("select f from FilmEntity f")
    List<FilmEntity> getAllFilm();

    @Query("select f from FilmEntity f where f.filmId = ?1") //?1 is the placeholder for the first parameter
    Optional<FilmEntity> findByFilmId(Integer filmId);
}
