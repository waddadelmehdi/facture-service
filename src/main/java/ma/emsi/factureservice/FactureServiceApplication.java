package ma.emsi.factureservice;

import ma.emsi.factureservice.entities.Facture;
import ma.emsi.factureservice.entities.ProductArticle;
import ma.emsi.factureservice.models.Client;
import ma.emsi.factureservice.models.Product;
import ma.emsi.factureservice.repositories.FactureRepository;
import ma.emsi.factureservice.repositories.ProductArticleRepository;
import ma.emsi.factureservice.service.ClientRestClient;
import ma.emsi.factureservice.service.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.hateoas.PagedModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class FactureServiceApplication implements CommandLineRunner {
    ClientRestClient clientRestClient;
    ProductRestClient productRestClient;
    FactureRepository factureRepository;
    ProductArticleRepository productArticleRepository;

    public FactureServiceApplication(ClientRestClient clientRestClient,ProductRestClient productRestClient,
                                     FactureRepository factureRepository,ProductArticleRepository productArticleRepository){
        this.clientRestClient = clientRestClient;
        this.productRestClient =productRestClient;
        this.factureRepository=factureRepository;
        this.productArticleRepository=productArticleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(FactureServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Client client = clientRestClient.getClientById(1L);
        Facture facture = factureRepository.save(new Facture(null,new Date(),null,client.getId(),client));
        List<Product> products = new ArrayList<>();
        PagedModel<Product> listProductsDB = productRestClient.pageProduct(0,3);
        for (Product product : listProductsDB) {
            ProductArticle productArticle = new ProductArticle();
            productArticle.setReference(product.getId());
            productArticle.setProduct(product);
            productArticle.setQuantity(1 + new Random().nextInt(10));
            productArticle.setFacture(facture);
            productArticle.setPrice(productArticle.getQuantity() * product.getPrice());
            productArticleRepository.save(productArticle);
        }

    }
}
