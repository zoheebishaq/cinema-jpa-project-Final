package org.vincimelun.cinemajpa.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="VOTES")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;

    @Basic
    @Column(name = "LIKED", nullable = false)
    private boolean like;

    @Basic
    @Column(name = "IP_ADRESS", nullable = false)
    private String ip;

    @ManyToOne
    @JoinColumn(name="FILM_ID")
    private Film film;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return id == vote.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, like, film);
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", like=" + like +
                ", film=" + film +
                '}';
    }
}
