package org.vincimelun.cinemajpa.dao;

import org.springframework.data.repository.CrudRepository;
import org.vincimelun.cinemajpa.model.Film;
import org.vincimelun.cinemajpa.model.Vote;

public interface VoteRepository extends CrudRepository<Vote, Long> {

}
