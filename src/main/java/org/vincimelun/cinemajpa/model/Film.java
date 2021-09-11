package org.vincimelun.cinemajpa.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="FILMS")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private long id;
    @Basic
    @Column(name = "TITLE", nullable = true, length = 50)
    private String titre;
    @Basic
    @Column(name = "IMAGE_PATH", nullable = true, length = 120)
    private String afficheNom;
    @Basic
    @Lob
    @Column(name = "SUMMARY", nullable = true)
    private String resume;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FILM_DIRECTOR")
    private Personne realisateur;
    @OneToMany(mappedBy = "film")
    private Collection<Role> acteurs;
    @OneToMany(mappedBy = "film")
    private Collection<Vote> votes = new ArrayList<>();
    @Transient
    private float note;
    @Transient
    private boolean hasVoted;

    public boolean isHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    public Collection<Vote> getVotes() {
        return votes;
    }

    public void setVotes(Collection<Vote> votes) {
        this.votes = votes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String title) {
        this.titre = title;
    }

    public float getNote() {
        float totalLikes = 0;
        if (votes.size() == 0) {
            return 0f;
        } else {
            //totalLikes = votes.stream().filter(vote -> vote.isLike()).count();
            totalLikes = votes.stream().filter(Vote::isLike).count();
            return totalLikes / votes.size() * 5;
        }
    }

    public String getAfficheNom() {
        return afficheNom;
    }

    public void setAfficheNom(String imagePath) {
        this.afficheNom = imagePath;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String summary) {
        this.resume = summary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film that = (Film) o;
        return id == that.id &&
                Objects.equals(titre, that.titre) &&
                Objects.equals(afficheNom, that.afficheNom) &&
                Objects.equals(resume, that.resume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titre, afficheNom, resume);
    }

    @Override
    public String toString() {
        return "Film{" +
                "titre='" + titre + '\'' +
                ", afficheNom='" + afficheNom + '\'' +
                '}';
    }


    public Personne getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(Personne realisateur) {
        this.realisateur = realisateur;
    }

    public Collection<Role> getActeurs() {
        return acteurs;
    }

    public void setActeurs(Collection<Role> playsById) {
        this.acteurs = playsById;
    }

}
