package ma.emsi.factureservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.factureservice.models.Product;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class ProductArticle {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long reference;
    private int quantity;
    private double price;

    @ManyToOne @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Facture facture;

    @Transient
    private Product product;

}
