package com.javaweb.demosb.service;

import com.javaweb.demosb.dto.FilmDTO;
import com.javaweb.demosb.entity.FilmEntity;
import com.javaweb.demosb.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<FilmDTO> getAllFilm() { // Đổi tên phương thức để phản ánh việc lấy tất cả bản ghi
        List<FilmEntity> entities = filmRepository.getAllFilm();
        return entities.stream()
                .map(entity -> modelMapper.map(entity, FilmDTO.class))
                .collect(Collectors.toList());
    }

//    public List<FilmDTO> getAllFilm() {
//        List<FilmEntity> entities = filmRepository.getAllFilm();
//        return FilmDTO.builder()
//                .filmId(posts.getFilmId())
//                .title(posts.getTitle())
//                .description(posts.getDescription())
//                .releaseYear(posts.getReleaseYear())
//                .languageId(posts.getLanguageId())
//                .originalLanguageId(posts.getOriginalLanguageId())
//                .rentalDuration(posts.getRentalDuration())
//                .rentalRate(posts.getRentalRate())
//                .length(posts.getLength())
//                .replacementCost(posts.getReplacementCost())
//                .rating(posts.getRating())
//                .specialFeatures(posts.getSpecialFeatures())
//                .lastUpdate(posts.getLastUpdate()).build();
//    }


}
