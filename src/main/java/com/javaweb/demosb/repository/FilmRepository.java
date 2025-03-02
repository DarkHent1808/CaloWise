package com.javaweb.demosb.repository;

import com.javaweb.demosb.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Short> {
    @Query("select f from FilmEntity f")
    List<FilmEntity> getAllFilm();

    
}
