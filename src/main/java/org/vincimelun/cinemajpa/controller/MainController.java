package org.vincimelun.cinemajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.vincimelun.cinemajpa.model.Film;
import org.vincimelun.cinemajpa.service.CinemaDataSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Controller
public class MainController {
    private final CinemaDataSource cinemaDataSource;

    @Autowired
    public MainController(CinemaDataSource cinemaDataSource) {
        this.cinemaDataSource = cinemaDataSource;
    }

    // Page d'accueil
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {

        Collection<Film> films = cinemaDataSource.getFilms();

        for(Film film: films) {
            film.setHasVoted(cinemaDataSource.hasVoted(film.getId(), request.getRemoteAddr()));
        }

        // Ajout de l'attribut films à la vue pour valeur : liste des films
        model.addAttribute("films", films);

        return "index";
    }

}
