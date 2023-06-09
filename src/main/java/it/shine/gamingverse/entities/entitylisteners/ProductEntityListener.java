package it.shine.gamingverse.entities.entitylisteners;

import it.shine.gamingverse.entities.Console;
import it.shine.gamingverse.entities.Game;
import it.shine.gamingverse.entities.Product;
import it.shine.gamingverse.repositories.ProductRepository;
import jakarta.persistence.PrePersist;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityListener {

    private static ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        ProductEntityListener.productRepository = productRepository;
    }

    @PrePersist
    @Transactional
    public void prePersist(Object entity) {
        if (entity instanceof Game || entity instanceof Console) {
            Product product = new Product();
            product.setId(((Product) entity).getId());
            product.setProductType(entity.getClass().getSimpleName().toLowerCase());

            productRepository.save(product);
        }
    }

}
