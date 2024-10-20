package ma.emsi.factureservice.repositories;

import ma.emsi.factureservice.entities.ProductArticle;
import ma.emsi.factureservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductArticleRepository extends JpaRepository<ProductArticle,Long> {
    List<Product> findByFactureId(Long id);

}
