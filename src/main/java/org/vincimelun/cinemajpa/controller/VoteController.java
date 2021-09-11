package org.vincimelun.cinemajpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.vincimelun.cinemajpa.errors.FilmNotFoundException;
import org.vincimelun.cinemajpa.model.Film;
import org.vincimelun.cinemajpa.model.Vote;
import org.vincimelun.cinemajpa.service.CinemaDataSource;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

@Controller
public class VoteController {
    private final CinemaDataSource cinemaDataSource;

    public VoteController(CinemaDataSource cinemaDataSource) {
        this.cinemaDataSource = cinemaDataSource;
    }


    // Traitement de la modification d'un film
    @GetMapping("/vote/{voteType}/{filmID}")
    public String postFilmEdit(@PathVariable(value = "voteType") boolean voteType, @PathVariable(value = "filmID") long filmID, Vote vote, HttpServletRequest request) {
        try {
            if(!cinemaDataSource.hasVoted(filmID, request.getRemoteAddr())) {
                Film film = cinemaDataSource.getFilm(filmID);
                vote.setFilm(film);
                vote.setLike(voteType);
                vote.setIp(request.getRemoteAddr());
                cinemaDataSource.saveVote(vote);
            }
            return "redirect:/";
        } catch(NoSuchElementException e) {
            throw new FilmNotFoundException();
        }
    }

}
