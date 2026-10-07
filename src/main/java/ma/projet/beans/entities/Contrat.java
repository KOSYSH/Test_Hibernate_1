package ma.projet.beans.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
@Entity
@Table(name="contrats")
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La date de début est obligatoire")
    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;
    @NotNull(message = "La date de fin est obligatoire")
    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutContrat staut;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id",nullable = false)

    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assurance_id",nullable = false)
    private Assurance assurance;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Assurance getAssurance() {
        return assurance;
    }

    public void setAssurance(Assurance assurance) {
        this.assurance = assurance;
    }

    public Contrat() {
    }

    public Contrat(LocalDate dateDebut, LocalDate dateFin, StatutContrat staut) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.staut = staut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public StatutContrat getStaut() {
        return staut;
    }

    public void setStaut(StatutContrat staut) {
        this.staut = staut;
    }
}
