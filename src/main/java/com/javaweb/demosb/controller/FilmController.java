package com.javaweb.demosb.controller;

import com.javaweb.demosb.dto.FilmDTO;
import com.javaweb.demosb.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping(value="/welcome")
    public String welcome(){
        return "Dit me chuck";
    }
    @GetMapping(value="/film")
    public List<FilmDTO> getFilm(){
        return filmService.getAllFilm();
    }
}
