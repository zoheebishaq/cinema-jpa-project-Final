package org.vincimelun.cinemajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.vincimelun.cinemajpa.errors.FilmNotFoundException;
import org.vincimelun.cinemajpa.errors.PersonneNotFoundException;
import org.vincimelun.cinemajpa.model.Film;
import org.vincimelun.cinemajpa.model.Personne;
import org.vincimelun.cinemajpa.model.Role;
import org.vincimelun.cinemajpa.service.CinemaDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class FilmController {
    private final CinemaDataSource cinemaDataSource;

    @Autowired
    public FilmController(CinemaDataSource cinemaDataSource) {
        this.cinemaDataSource = cinemaDataSource;
    }

    // Page d'affichage d'un film
    @GetMapping("/film/{filmID}")
    public String getFilm(@PathVariable(value = "filmID") long filmID, Model model) {

        try {

            // Récupération des informations du film avec l'id passé dans l'url
            Film film = cinemaDataSource.getFilm(filmID);

            // Récupération des acteurs pour chaque rôle du film
            List<Personne> acteursFilm = new ArrayList<Personne>();
            for (Role role : film.getActeurs()) {
                acteursFilm.add(role.getActeur());
            }

            // Ajout de l'attribut film à la vue pour valeur : Film (trouvé avec un id)
            model.addAttribute("film", film);
            // Ajout de l'attribut acteursFilm à la vue pour valeur : liste des rôles du film
            model.addAttribute("acteursFilm", acteursFilm);
            return "film";

        } catch (NoSuchElementException e) {
            // Erreur qui se déclenche quand on ne trouve pas le film avec son id
            throw new FilmNotFoundException();
        }

    }

    // Page de modification d'un film
    @GetMapping("/film/{filmID}/edit")
    public String getFilmEdit(@PathVariable(value = "filmID") long filmID, Model model) {

        try {
            // Ajout de l'attribut film à la vue pour valeur : Film (trouvé avec un id)
            model.addAttribute("film", cinemaDataSource.getFilm(filmID));
        } catch (NoSuchElementException e) {
            // Erreur qui se déclenche quand on ne trouve pas le film avec son id
            throw new FilmNotFoundException();
        }

        try {
            // Ajout de l'attribut personnes à la vue pour valeur : liste des personnes (acteurs/réalisateurs)
            model.addAttribute("personnes", cinemaDataSource.getPersonnes());
        } catch (NoSuchElementException e) {
            // Erreur qui se déclenche quand on ne trouve pas le film avec son id
            throw new PersonneNotFoundException();
        }

        return "filmForm";


    }

    // Traitement de la modification d'un film
    @PostMapping("/film/{filmID}/edit")
    public String postFilmEdit(@ModelAttribute(name = "film") Film film, @PathVariable(value = "filmID") long filmID) {

        try {
            // Récupération du film dans la bdd grâce à son id
            Film filmDB = cinemaDataSource.getFilm(filmID);

            // Assignation des valeurs du nouveau film dans celui déjà dans la bdd
            filmDB.setTitre(film.getTitre());
            filmDB.setResume(film.getResume());
            filmDB.setAfficheNom(film.getAfficheNom());
            filmDB.setRealisateur(film.getRealisateur());

            // Sauvegarde du film dans la bdd
            cinemaDataSource.saveFilm(filmDB);
        } catch (NoSuchElementException e) {
            // Erreur qui se déclenche quand on ne trouve pas le film avec son id
            throw new FilmNotFoundException();
        }

        // Redirige vers la page d'affichage du film
        return "redirect:/film/" + filmID;
    }

    // Page d'ajout d'un film
    @GetMapping("/film/add")
    public String getFilmAdd(Model model) {

        try {
            // Ajout de l'attribut personnes à la vue pour valeur : liste des personnes (acteurs/réalisateurs)
            model.addAttribute("personnes", cinemaDataSource.getPersonnes());
        } catch (NoSuchElementException e) {
            // Erreur qui se déclenche quand on ne trouve pas le film avec son id
            throw new PersonneNotFoundException();
        }

        return "filmForm";
    }

    // Traitement de l'ajout d'un film
    @PostMapping("/film/add")
    public String postFilmAdd(@ModelAttribute(name = "film") Film film) {

        // Sauvegarde du film créé par le "@ModelAttribute" dans la bdd
        cinemaDataSource.saveFilm(film);

        return "filmForm";
    }

}
