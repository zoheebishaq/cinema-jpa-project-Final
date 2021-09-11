package org.vincimelun.cinemajpa.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vincimelun.cinemajpa.dao.FilmRepository;
import org.vincimelun.cinemajpa.dao.PersonneRepository;
import org.vincimelun.cinemajpa.dao.RoleRepository;
import org.vincimelun.cinemajpa.dao.VoteRepository;
import org.vincimelun.cinemajpa.errors.FilmNotFoundException;
import org.vincimelun.cinemajpa.model.Film;
import org.vincimelun.cinemajpa.model.Personne;
import org.vincimelun.cinemajpa.model.Role;
import org.vincimelun.cinemajpa.model.Vote;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CinemaDataSource {
    FilmRepository filmRepository;
    PersonneRepository personneRepository;
    RoleRepository roleRepository;
    VoteRepository voteRepository;

    @Autowired
    public CinemaDataSource(FilmRepository filmRepository, PersonneRepository personneRepository, RoleRepository roleRepository, VoteRepository voteRepository){
        this.filmRepository = filmRepository;
        this.personneRepository = personneRepository;
        this.roleRepository = roleRepository;
        this.voteRepository = voteRepository;
    }

    public List<Film> getFilms(){
        return Lists.newArrayList(filmRepository.findAll());
    }

    public Film getFilm(long id) {
        return filmRepository.findById(id).get();
    }

    public void saveFilm(Film film) {
        filmRepository.save(film);
    }

    public List<Personne> getPersonnes(){
        return Lists.newArrayList(personneRepository.findAll());
    }

    public Personne getPersonne(long personID) {
        return personneRepository.findById(personID).get();
    }

    public void savePersonne(Personne person) {
        personneRepository.save(person);
    }

    public void deleteRole(long id) {
        roleRepository.delete(roleRepository.findById(id).get());
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public void saveVote(Vote vote) {
        voteRepository.save(vote);
    }

    public boolean hasVoted(long filmID, String ip) {
        boolean hasVoted = false;
        try {
            Film film = this.getFilm(filmID);
            List<Vote> votes = (List<Vote>) voteRepository.findAll();
            for(Vote vote: votes) {
                if (vote.getFilm().equals(film) && vote.getIp().equals(ip)) {
                    hasVoted = true;
                    break;
                }
            }
        } catch(NoSuchElementException e) {
            throw new FilmNotFoundException();
        }
        return hasVoted;
    }
}
