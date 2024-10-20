package ma.emsi.factureservice.controllers;

import ma.emsi.factureservice.entities.Facture;
import ma.emsi.factureservice.models.Product;
import ma.emsi.factureservice.repositories.FactureRepository;
import ma.emsi.factureservice.repositories.ProductArticleRepository;
import ma.emsi.factureservice.service.ClientRestClient;
import ma.emsi.factureservice.service.ProductRestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FactureRestController {
    FactureRepository factureRepository;
    ClientRestClient clientRestClient;
    ProductRestClient productRestClient;
    ProductArticleRepository productArticleRepository;

    public FactureRestController(FactureRepository factureRepository,ClientRestClient clientRestClient,
                                 ProductRestClient productRestClient,ProductArticleRepository productArticleRepository){
        this.factureRepository=factureRepository;
        this.clientRestClient=clientRestClient;
        this.productRestClient=productRestClient;
        this.productArticleRepository=productArticleRepository;
    }

    @GetMapping("/factures/{id}")
    public Facture getFacture(@PathVariable Long id){
        Facture facture=factureRepository.findById(id).get();
        facture.setClient(clientRestClient.getClientById(facture.getIdClient()));
        facture.getListProducts().forEach(productArticle -> {
            Product product = productRestClient.getProductById(productArticle.getReference());
        });
        return facture;
    }

    @GetMapping("/factures")
    public List<Facture> getAll(){
        System.out.println("list factures");
        return factureRepository.findAll();
    }


}
