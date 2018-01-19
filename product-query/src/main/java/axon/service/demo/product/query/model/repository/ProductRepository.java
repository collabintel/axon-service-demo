package axon.service.demo.product.query.model.repository;

import axon.service.demo.product.query.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findAllByProductName(String productName);

}
