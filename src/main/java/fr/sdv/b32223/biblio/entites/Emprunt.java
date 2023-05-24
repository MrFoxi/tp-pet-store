package fr.sdv.b32223.biblio.entites;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="EMPRUNT")
public class Emprunt {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "DATE_DEBUT", nullable = false)
    private LocalDate dateD;

    @Column(name = "DATE_FIN", nullable = false)
    private LocalDate dateF;

    @Column(name = "DELAI")
    private int delai;

    @ManyToOne
    @JoinColumn(name="ID_CLIENT")
    Client idClient;

    @ManyToMany
    @JoinTable(name = "COMPO", joinColumns = @JoinColumn(name = "ID_LIV"), inverseJoinColumns = @JoinColumn(name = "ID_EMP"))
    Set<Livre> livres;

    public int getId() {
        return id;
    }

    public LocalDate getDateD() {
        return dateD;
    }

    public void setDateD(LocalDate dateD) {
        this.dateD = dateD;
    }

    public LocalDate getDateF() {
        return dateF;
    }

    public void setDateF(LocalDate dateF) {
        this.dateF = dateF;
    }

    public int getDelai() {
        return delai;
    }

    public void setDelai(int delai) {
        this.delai = delai;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    public Set<Livre> getLivres() {
        return livres;
    }

    public void setLivres(Set<Livre> livres) {
        this.livres = livres;
    }
    @Override
    public String toString() {
        return "Emprunt n°"+ id + ", du " + dateD + " au " + dateF + " (délai de " + delai + " jours)";
    }

}