package ma.projet.beans.entities;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name ="assurances")
@NamedQuery(name = "Assurance.TypeRech",query = "SELECT a from Assurance a WHERE a.type = :type")

public class Assurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le type est obligatoire")
    @Column(nullable = false)
    private String type;

    @NotNull(message = "Le montant est obligatoire ")
    @Column(nullable = false)
    private double montnat;

    @NotBlank
    @Column(nullable = false)
    private String couverture;
    @OneToMany(mappedBy = "assurance",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Contrat> contrats = new ArrayList<>();

    public List<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(List<Contrat> contrats) {
        this.contrats = contrats;
    }

    public Assurance() {
    }

    public Assurance(String type, double montnat, String couverture) {
        this.type = type;
        this.montnat = montnat;
        this.couverture = couverture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMontnat() {
        return montnat;
    }

    public void setMontnat(double montnat) {
        this.montnat = montnat;
    }

    public String getCouverture() {
        return couverture;
    }

    public void setCouverture(String couverture) {
        this.couverture = couverture;
    }
}
