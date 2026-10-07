package ma.projet.beans.entities;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@NamedQuery(
        name  = "Client.contratsByCin",
        query = "SELECT co FROM Contrat co JOIN co.client c WHERE c.cin = :cin"
)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le CIN est Obligatoire")
    @Column(unique = true, nullable = false)
    private String cin;

    @NotBlank(message = "Le nom est Obligatoire")
    @Column(nullable = false)
    private String nom;

    @NotBlank(message = "Le prénom est Obligatoire")
    @Column(nullable = false)
    private String prenom;

    @Email(message = "Le format de l'email n'est pas valide")
    @Column(nullable = false, unique = true)
    @NotBlank(message = "L'email est obligatoire")
    private String email;

    private String telephone;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contrat> contrats = new ArrayList<>();

    public List<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(List<Contrat> contrats) {
        this.contrats = contrats;
    }

    public Client() {
    }

    public Client(String cin, String nom, String prenom, String email, String telephone) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
