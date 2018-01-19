package axon.service.demo.product.query.model.repository;

import axon.service.demo.product.query.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

    Page<Product> findAllByProductName(String productName);

}
