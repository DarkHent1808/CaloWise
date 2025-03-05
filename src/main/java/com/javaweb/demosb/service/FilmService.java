package com.javaweb.demosb.service;

import com.javaweb.demosb.dto.FilmDTO;
import com.javaweb.demosb.entity.FilmEntity;
import com.javaweb.demosb.exception.BusinessException;
import com.javaweb.demosb.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class FilmService {

    private final FilmRepository filmRepository;
    private final ModelMapper modelMapper;

    public FilmService(FilmRepository filmRepository, ModelMapper modelMapper) {
        this.filmRepository = filmRepository;
        this.modelMapper = modelMapper;
    }
    public List<FilmDTO> getAllFilm() {
        List<FilmEntity> entities = filmRepository.getAllFilm();
        return entities.stream()
                .map(entity -> modelMapper.map(entity, FilmDTO.class))
                .collect(Collectors.toList());
    }
    public FilmDTO getFilmById(Integer filmId) {
        Optional<FilmEntity> entityOptional = filmRepository.findByFilmId(filmId);
        FilmEntity entity;
        if(entityOptional.isPresent()) {
            entity = entityOptional.get();
        }
        else {
            throw new RuntimeException("Film not found");
        }
        return FilmDTO.builder()    //manual mapping
                .filmId(entity.getFilmId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .releaseYear(entity.getReleaseYear())
                .languageId(entity.getLanguageId())
                .originalLanguageId(entity.getOriginalLanguageId())
                .rentalDuration(entity.getRentalDuration())
                .rentalRate(entity.getRentalRate())
                .length(entity.getLength())
                .replacementCost(entity.getReplacementCost())
                .rating(entity.getRating())
                .specialFeatures(entity.getSpecialFeatures())
                .lastUpdate(entity.getLastUpdate()).build();
    }
    public List<FilmDTO> testException() {
        throw new BusinessException("địt mẹ Chuck"); //only used in Service layer
    }

}
