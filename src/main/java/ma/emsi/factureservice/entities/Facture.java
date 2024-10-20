package ma.emsi.factureservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.factureservice.models.Client;

import java.util.Collection;
import java.util.Date;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Facture {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dateFacture;


    @OneToMany(mappedBy = "facture")
    private Collection<ProductArticle> listProducts;

    private Long idClient;

    @Transient
    private Client client;
}
