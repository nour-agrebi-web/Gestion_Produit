package Model;
import jakarta.persistence.*;

@Entity
@Table(name="PRODUITS")

public class produits {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

@Column(name="ID_PRODUIT")
private Long idProduit;

@Column(name="NOM_PRODUIT")
private String nomProduit;

@Column(name="PRIX")
private double prix;

public produits() {
}

public produits(String nomProduit, double prix) {
this.nomProduit = nomProduit;
this.prix = prix;
}

public Long getIdProduit() {
return idProduit;
}

public void setIdProduit(Long idProduit) {
this.idProduit = idProduit;
}

public String getNomProduit() {
return nomProduit;
}

public void setNomProduit(String nomProduit) {
this.nomProduit = nomProduit;
}

public double getPrix() {
return prix;
}

public void setPrix(double prix) {
this.prix = prix;
}

}
