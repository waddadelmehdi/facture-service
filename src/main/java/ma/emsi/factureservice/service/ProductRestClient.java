package ma.emsi.factureservice.service;

import ma.emsi.factureservice.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductRestClient {
    @GetMapping(path = "/products")
    PagedModel<Product> pageProduct(@RequestParam(value = "page") int page,
                                    @RequestParam(value = "taille") int taille);

    @GetMapping(path = "/products/{id}")
    Product getProductById(@PathVariable Long id);
}
