package org.vincimelun.cinemajpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.vincimelun.cinemajpa.errors.FilmNotFoundException;
import org.vincimelun.cinemajpa.errors.PersonneNotFoundException;
import org.vincimelun.cinemajpa.model.Film;
import org.vincimelun.cinemajpa.model.Personne;
import org.vincimelun.cinemajpa.service.CinemaDataSource;

import java.util.NoSuchElementException;

@Controller
public class PersonneController {
    private final CinemaDataSource cinemaDataSource;

    @Autowired
    public PersonneController(CinemaDataSource cinemaDataSource) {
        this.cinemaDataSource = cinemaDataSource;
    }

    // Page d'affichage d'une personne (acteur/réalisateur)
    @GetMapping("/personne/{personneID}")
    public String getPersonne(@PathVariable(name = "personneID") long personneID, @RequestParam(name = "film") long filmID, Model model) {

        try {

            // Récupération de la personne liée à l'id passé par l'url
            Personne personne = cinemaDataSource.getPersonne(personneID);

            // Ajout de l'attribut personne à la vue pour valeur : Personne liée à l'id
            model.addAttribute("personne", personne);

        } catch (NoSuchElementException e) {
            // Erreur qui se déclenche quand on ne trouve pas le film avec son id
            throw new PersonneNotFoundException();
        }

        try {

            // Récupération du film lié à l'id passé en paramètre
            Film film = cinemaDataSource.getFilm(filmID);

            // Ajout de l'attribut filmID à la vue pour valeur : ID du film qui a mené à cette page
            model.addAttribute("film", film);

        } catch (NoSuchElementException e) {
            // Erreur qui se déclenche quand on ne trouve pas le film avec son id
            throw new FilmNotFoundException();
        }

        return "personne";
    }

    // Page de modification d'une personne
    @GetMapping("/personne/{personneID}/edit")
    public String getPersonneEdit(@PathVariable(name = "personneID") long personneID, @RequestParam(name = "film") long filmID, Model model) {

        try {

            // Récupération de la personne liée à l'id passé par l'url
            Personne personne = cinemaDataSource.getPersonne(personneID);

            // Ajout de l'attribut personne à la vue pour valeur : Personne liée à l'id
            model.addAttribute("personne", personne);

        } catch (NoSuchElementException e) {
            // Erreur qui se déclenche quand on ne trouve pas le film avec son id
            throw new PersonneNotFoundException();
        }

        // Ajout de l'attribut filmID à la vue pour valeur : ID du film qui a mené à cette page
        model.addAttribute("filmID", filmID);

        return "personneForm";
    }

    // Traitement de la modification d'une personne
    @PostMapping("/personne/{personneID}/edit")
    public String postPersonneEdit(@PathVariable(name = "personneID") long personneID, @RequestParam(name = "film") long filmID, @ModelAttribute(name = "personne") Personne personne) {

        try {

            // Récupération de la personne liée à l'id passé par l'url
            Personne personneDB = cinemaDataSource.getPersonne(personneID);

            // Assignation des valeurs de la nouvelle personne dans celle déjà dans la bdd
            personneDB.setPhoto(personne.getPhoto());
            personneDB.setNom(personne.getNom());
            personneDB.setPrenom(personne.getPrenom());
            personneDB.setAnneeNaiscance(personne.getAnneeNaiscance());

        } catch (NoSuchElementException e) {
            // Erreur qui se déclenche quand on ne trouve pas le film avec son id
            throw new PersonneNotFoundException();
        }

        // Sauvegarde de la personne déjà existante dans la bdd
        cinemaDataSource.savePersonne(personne);

        // Redirige vers la page d'affichage de la personne modifiée
        String paramFilmID = "?film=" + filmID;
        return "redirect:/personne/" + personneID + paramFilmID;
    }

    // Page d'ajout d'une personne
    @GetMapping("/personne/add")
    public String getPersonneAdd() {
        return "personneForm";
    }

    // Traitement de l'ajout d'une personne
    @PostMapping("/personne/add")
    public String postPersonneAdd(@ModelAttribute(name = "personneAdd") Personne personne, @RequestParam(name = "film") long filmID) {

        // Si on envoi pas de photo, la photo par défaut est ajoutée
        if (personne.getPhoto() == null) personne.setPhoto("person.png");

        // Sauvegarde de la nouvelle personne dans la bdd
        cinemaDataSource.savePersonne(personne);

        // Redirige vers la page de la personne qui vient d'être ajoutée
        String paramFilmID = "?film=" + filmID;
        return "redirect:/personne/" + personne.getId() + paramFilmID;
    }

}
