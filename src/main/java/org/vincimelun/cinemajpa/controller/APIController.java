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
import org.vincimelun.cinemajpa.model.RoleForm;
import org.vincimelun.cinemajpa.service.CinemaDataSource;

import java.util.NoSuchElementException;

@Controller
public class APIController {
    private final CinemaDataSource cinemaDataSource;

    @Autowired
    public APIController(CinemaDataSource cinemaDataSource) {
        this.cinemaDataSource = cinemaDataSource;
    }

    // Traitement de la suppression d'un rôle d'un film
    @GetMapping("/role/{roleID}/delete")
    public String getRoleDelete(@PathVariable(value = "roleID") long roleID) {

        // Supprime le rôle trouvé avec l'id passé en url
        cinemaDataSource.deleteRole(roleID);


        // La vue de retour est au format JSON
        return "reponseRoleSupprime";
    }

    // Traitement de l'ajout d'un rôle à un film
    @PostMapping("/role/add/{filmID}")
    public String getRoleAdd(@PathVariable(name = "filmID") long filmID, @ModelAttribute RoleForm body, Role role, Model model) {

        try {

            // Récupération de l'acteur lié à l'id envoyé par le formulaire
            Personne acteur = cinemaDataSource.getPersonne(body.getActorID());

            // Assignation des valeurs récupérées au nouveau rôle
            role.setActeur(acteur);

            // Ajout de l'attribut actor à la vue pour valeur : Personne qui à été ajoutée au film en tant qu'acteur
            model.addAttribute("actor", acteur);

        } catch (NoSuchElementException e) {
            // Erreur qui se déclenche quand on ne trouve pas le film avec son id
            throw new PersonneNotFoundException();
        }

        try {

            // Récupération du film lié à l'id passé par l'url
            Film film = cinemaDataSource.getFilm(filmID);

            // Assignation des valeurs récupérées au nouveau rôle
            role.setFilm(film);

        } catch (NoSuchElementException e) {
            // Erreur qui se déclenche quand on ne trouve pas le film avec son id
            throw new FilmNotFoundException();
        }

        // Assignation des valeurs récupérées au nouveau rôle
        role.setName(body.getName());

        // Sauvegarde du nouveau rôle dans la bdd
        Role addedRole = cinemaDataSource.saveRole(role);

        // Ajout de l'attribut roleID à la vue pour valeur : ID du rôle ajouté à la bdd
        model.addAttribute("roleID", addedRole.getId());

        // La vue de retour est au format JSON
        return "reponseRoleAjoute";
    }
}
