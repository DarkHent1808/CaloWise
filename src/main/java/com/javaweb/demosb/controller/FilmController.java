package com.javaweb.demosb.controller;

import com.javaweb.demosb.dto.FilmDTO;
import com.javaweb.demosb.dto.ResponseDTO;
import com.javaweb.demosb.service.FilmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/film")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping(value="/welcome")
    public String welcome(){
        return "Dit me chuck";
    }
    @GetMapping(value="/all")
    public ResponseEntity<ResponseDTO<List<FilmDTO>>> getAllFilm(){
        List<FilmDTO> films = filmService.getAllFilm();
        ResponseDTO<List<FilmDTO>> response = ResponseDTO.<List<FilmDTO>>builder()
                .data(films).message("Success")
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<FilmDTO> getFilmById(@RequestParam("filmId") Integer filmId){
        return ResponseEntity.ok(filmService.getFilmById(filmId));
    }

    @GetMapping(value="/exception")
    public ResponseEntity<List<FilmDTO>> testException(){
        return ResponseEntity.ok(filmService.testException());
    }
}
